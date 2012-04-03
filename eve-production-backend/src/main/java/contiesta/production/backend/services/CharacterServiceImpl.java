package contiesta.production.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import contiesta.production.backend.marshallers.CharacterSheetMarshaller;
import contiesta.production.backend.marshallers.CharactersMarshaller;
import contiesta.production.backend.models.ApiContext;
import contiesta.production.backend.models.EveCharacter;
import contiesta.production.backend.models.IndustryContext;
import contiesta.production.backend.models.ItemType;
import contiesta.production.backend.models.Job;
import contiesta.production.backend.models.JobContext;
import contiesta.production.backend.models.ScienceContext;
import contiesta.production.backend.models.Skill;
import contiesta.production.backend.models.TrainedSkill;
import contiesta.production.backend.repos.CharacterRepo;
import contiesta.production.backend.utils.ApiServiceUtils;

@Service
public class CharacterServiceImpl implements CharacterService{

	@Autowired
	private CharacterRepo characterRepo;
	
	@Autowired
	private CharactersMarshaller charactersMarshaller;
	@Autowired
	private CharacterSheetMarshaller characterSheetMarshaller;
	
	private static final Logger logger = LoggerFactory.getLogger(CharacterServiceImpl.class);
	
	/**
	 * 
	 * @param context
	 * @return True - ApiContext was saved to database with EveCharacter's associated with it
	 * False - ApiContext already exists
	 */
	@Transactional
	public boolean createApiContext(ApiContext context)
	{
		List<EveCharacter> chars = new ArrayList<EveCharacter>();
		for(String c : findEveCharacterIdsApiContext(context))
		{
			chars.add(findCharacterSheet(context, c));
		}
		if(chars.isEmpty())
		{
			return false;
		}
		context.setEveCharacters(chars);
		characterRepo.save(context);
		return true;
	}

	@Transactional
	public List<ApiContext> findAllApiContext()
	{
		return characterRepo.findAllApiContext();
	}
	
	@Transactional
	public List<EveCharacter> findEveCharactersByKeyId(String id)
	{
		ApiContext context = characterRepo.findByContextKey(id);
		List<EveCharacter> retVal = new ArrayList<EveCharacter>();
		for(EveCharacter e : context.getEveCharacters())
		{
			retVal.add(e);
		}
		return retVal;
	}
	
	public List<String> findEveCharacterIdsApiContext(ApiContext context) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> re = rt.getForEntity(ApiServiceUtils.CHARACTERS_SERVICE, String.class, context.getKeyId(), context.getVerificationCode());
		return charactersMarshaller.findCharacterIds(re.getBody());
	}
	
	public EveCharacter findCharacterSheet(ApiContext context, String id)
	{
		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> re = rt.getForEntity(ApiServiceUtils.CHARACTER_SHEET_SERVICE, String.class, context.getKeyId(), context.getVerificationCode(), id);
		EveCharacter retVal = characterSheetMarshaller.unmarshallXMLToObject(re.getBody());
		retVal.setApiContext(context);
		return retVal;
	}

	@Transactional
	public void removeApiContext(String id) {
		ApiContext context = characterRepo.findByContextKey(id);
		characterRepo.delete(context);
	}

	@Transactional
	public List<JobContext> findAllIndustryJobs() {
		List<ApiContext> contexts = characterRepo.findAllApiContext();
		List<JobContext> jbs = new ArrayList<JobContext>();
		RestTemplate rt = new RestTemplate();
		for(ApiContext c : contexts)
		{
			for(EveCharacter e : c.getEveCharacters())
			{
				JobContext job = new JobContext();
				job.setIndustryContext(new IndustryContext());
				job.setScienceContext(new ScienceContext());
				ResponseEntity<String> entity = rt.getForEntity(ApiServiceUtils.CHARACTERS_INDUSTRY_JOBS, String.class, c.getKeyId(), c.getVerificationCode(), e.getCharacterId());
				job.setJobs(charactersMarshaller.findNumberOfJobsForCharacter(entity.getBody()));
				parseJobs(job);
				job.setName(e.getName());
								   // Advanced Mass Production
				job.getIndustryContext().setTotalPossible(characterRepo.findSkillLevelForTrainedSkill(e.getCharacterId(), 24625) 
								   // Mass Production
								   + characterRepo.findSkillLevelForTrainedSkill(e.getCharacterId(), 3387)
								   // +1 for every character having 1 initial job
								   + 1);
								   // Laboratory Operation
				job.getScienceContext().setTotalPossible(characterRepo.findSkillLevelForTrainedSkill(e.getCharacterId(), 3406) 
								   // Advanced Laboratory Operation
								   + characterRepo.findSkillLevelForTrainedSkill(e.getCharacterId(), 24624)
								   // +1 for every character having 1 initial job
								   + 1);
				
				jbs.add(job);
			}
		}
		return jbs;
	}
	
	private void parseJobs(JobContext jc)
	{
		for(Job j : jc.getJobs())
		{
			if(j.getBlueprintTypeId() == j.getOutputTypeId())
			{
				jc.getScienceContext().setInProgress(jc.getScienceContext().getInProgress() + 1);
			}
			else if (j.getBlueprintTypeId() != j.getOutputTypeId())
			{
				// Output is a blueprint but type id's match, therefore it's invention, increase science context
				if(characterRepo.findByKey(ItemType.class, j.getOutputTypeId()).getTypeName().contains("Blueprint"))
				{
					jc.getScienceContext().setInProgress(jc.getScienceContext().getInProgress() + 1);
				}
				else
				{
					jc.getIndustryContext().setInProgress(jc.getIndustryContext().getInProgress() + 1);
				}
			}
		}
	}
}
