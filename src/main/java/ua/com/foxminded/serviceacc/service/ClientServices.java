package ua.com.foxminded.serviceacc.service;

import java.util.ArrayList;

import javax.inject.Named;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import ua.com.foxminded.serviceacc.model.dao.ClientDao;
import ua.com.foxminded.serviceacc.model.domain.Client;

@Named
public class ClientServices {
	private static final Logger log = Logger.getLogger("ClientService : ");

	@Autowired
	private ClientDao clientDao;

	public ArrayList<Client> getAll() {
		log.info("getAll()");
		return (ArrayList<Client>) clientDao.getAll();
	}

	public void save(Client client) {
		log.info("save(ClientAddNew client: " + client.getFirstName() + " " + client.getSecondName());
		clientDao.save(client);
	}

	public void update(Client client) {
		log.info("update(firstName = " + client.getFirstName() + " secondName = " + client.getSecondName() + ")");
		clientDao.save(client);
	}

	public void delete(Client client) {
		clientDao.delete(client);
	}

}
