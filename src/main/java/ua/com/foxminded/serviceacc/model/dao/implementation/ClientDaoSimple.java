package ua.com.foxminded.serviceacc.model.dao.implementation;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ua.com.foxminded.serviceacc.model.dao.ClientDao;
import ua.com.foxminded.serviceacc.model.domain.Client;

public class ClientDaoSimple implements ClientDao {
	private static final Logger log = Logger.getLogger("ClientsDaoSimpl : ");
	private static SessionFactory sessionFactory;

	public ClientDaoSimple() {
		new HibernateUtil();
	}

	@Override
	public List<Client> getAll() {
		log.info("dao.getAll()");
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Client> buffer = session.createCriteria(Client.class).list();
		ArrayList<Client> result = new ArrayList<Client>();
		for (Client bufferClient : buffer) {
			Client resultClient = new Client();
			resultClient.setId(bufferClient.getId());
			resultClient.setFirstName(bufferClient.getFirstName());
			resultClient.setSecondName(bufferClient.getSecondName());
			resultClient.setStatus(bufferClient.getStatus());
			resultClient.setLevel(bufferClient.getLevel());
			log.info("getAll " + resultClient.getFirstName() + " " + resultClient.getSecondName());
			result.add(resultClient);
		}
		session.getTransaction().commit();
		return result;
	}

	@Override
	public void save(Client client) {
		log.info("dao.save()");
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(client);
		session.getTransaction().commit();

	}

	@Override
	public void delete(Client client) {
		log.info("dao.delete()");
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		sessionFactory.getCurrentSession().delete(client);
		session.getTransaction().commit();

	}
}
