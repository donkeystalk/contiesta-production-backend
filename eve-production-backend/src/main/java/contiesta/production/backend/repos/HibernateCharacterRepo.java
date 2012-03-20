package contiesta.production.backend.repos;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import contiesta.production.backend.models.EveCharacter;

@Repository
public class HibernateCharacterRepo implements CharacterRepo{

	private HibernateTemplate hibernateTemplate;
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	public EveCharacter findCharacterByName(String name) {
		return hibernateTemplate.get(EveCharacter.class, name);
	}

}
