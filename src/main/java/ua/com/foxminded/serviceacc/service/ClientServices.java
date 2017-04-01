package ua.com.foxminded.serviceacc.service;

import java.util.ArrayList;

import javax.inject.Named;

import org.apache.log4j.Logger;

import ua.com.foxminded.serviceacc.model.domain.Client;

@Named
public class ClientServices {
	private static final Logger log = Logger.getLogger("ClientService : ");
	private ArrayList<Client> stub;
	private static int id = 1;

	public ClientServices() {
		stub = new ArrayList<>();
		Client client = new Client();
		client.setId(id++);
		client.setFirstName("Sasha");
		client.setSecondName("Mckey");
		client.setLevel(0);
		client.setStatus(0);
		stub.add(client);
		client = new Client();
		client.setId(id++);
		client.setFirstName("Dima");
		client.setSecondName("Mckey");
		client.setLevel(0);
		client.setStatus(0);
		stub.add(client);
		client = new Client();
		client.setId(id++);
		client.setFirstName("Vasya");
		client.setSecondName("Pupkin");
		client.setLevel(0);
		client.setStatus(0);
		stub.add(client);
		client = new Client();
		client.setId(id++);
		client.setFirstName("Ivan");
		client.setSecondName("Ivanovich");
		client.setLevel(0);
		client.setStatus(0);
		stub.add(client);

	}

	public ArrayList<Client> getAll() {
		log.info("getAll()");
		return stub;
	}

	public void save(Client client) {
		log.info("save(ClientAddNew client: " + client.getFirstName() + " " + client.getSecondName());
		client.setId(id++);
		if (stub.add(client)) {
			log.info("save - OK");
		} else {
			log.info("save - Error");
		}
	}

	public void update(Client client) {
		log.info("update(firstName = " + client.getFirstName() + " secondName = " + client.getSecondName() + ")");
		if (stub.contains(client)) {
			int index = stub.indexOf(client);
			stub.set(index, client);
			log.info("updated - OK");
		} else {
			log.info("updated - Error");
		}
	}

	public void delete(Client client) {
		if (stub.remove(client)) {
			log.info("deleted");
		} else {
			log.info("Error: deleted");
		}
	}

}
