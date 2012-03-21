package contiesta.production.backend.repos;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import contiesta.production.backend.models.EveCharacter;

@Repository
public class HibernateCharacterRepo implements CharacterRepo{

	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	public EveCharacter findCharacterByName(String name) {
		return hibernateTemplate.get(EveCharacter.class, name);
	}

	public void saveCharacter(EveCharacter character)
	{
		hibernateTemplate.save(character);
	}
}
