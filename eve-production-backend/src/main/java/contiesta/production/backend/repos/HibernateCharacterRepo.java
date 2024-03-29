package contiesta.production.backend.repos;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import contiesta.production.backend.models.ApiContext;
import contiesta.production.backend.models.Skill;
import contiesta.production.backend.models.TrainedSkill;

@Repository
public class HibernateCharacterRepo implements CharacterRepo{

	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	public <T> void save(T object) {
		hibernateTemplate.save(object);
	}

	public List<ApiContext> findAllApiContext() {
		return hibernateTemplate.find("from ApiContext");
	}

	public <T> void delete(T obj) {
		hibernateTemplate.delete(obj);
	}

	public <T> T findByKey(Class<T> c, int key) {
		return hibernateTemplate.get(c, key);
	}

	public ApiContext findByContextKey(String id) {
		return (ApiContext)hibernateTemplate.find("from ApiContext where keyId = ?", id).get(0);
	}

	public int findSkillLevelForTrainedSkill(int characterId, int typeId) {
		String[] paramNames = new String[]{"characterId", "typeId"};
		Object[] params = new Object[]{characterId, typeId};
		List<TrainedSkill> skills = hibernateTemplate.findByNamedParam("from TrainedSkill where characterId = :characterId and typeId = :typeId", paramNames, params);
		if(skills != null && skills.size() == 1)
		{
			return skills.get(0).getLevel();
		}
		return 0;
	}	
	
}
