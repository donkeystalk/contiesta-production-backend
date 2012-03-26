package contiesta.production.backend.repos;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import contiesta.production.backend.models.ApiContext;

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

	@Override
	public <T> T findByKey(Class<T> c, int key) {
		return hibernateTemplate.get(c, key);
	}
}
