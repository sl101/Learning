package ua.com.foxminded.serviceacc.controller.client;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import ua.com.foxminded.serviceacc.model.Client;
import ua.com.foxminded.serviceacc.model.Person;
import ua.com.foxminded.serviceacc.service.ClientService;
import ua.com.foxminded.serviceacc.service.PersonService;

@Named
public class ClientList implements Serializable {

	private static final long serialVersionUID = 1L;

	private ArrayList<Client> list;

	private boolean isShowClients = false;

	private boolean isBlockTable;

	@Inject
	private ClientService clientService;

	@Inject
	private PersonService personService;

	@PostConstruct
	public void init() {
		updateData();
	}

	public void updateData() {
		Person person1 = new Person();
		person1.setFirstName("firstName1");
		person1.setLastName("lastName1");
		person1 = personService.create(person1);

		Person person2 = new Person();
		person2.setFirstName("firstName2");
		person2.setLastName("lastName2");
		person2 = personService.create(person2);

		Client client1 = new Client();
		Client client2 = new Client();

		client1 = clientService.create(client1);
		client1.setPerson(person1);
		clientService.create(client1);

		client2 = clientService.create(client2);
		client2.setPerson(person2);
		clientService.create(client2);

		list = (ArrayList<Client>) clientService.findAll();
	}

	public boolean getIsShowClients() {
		return isShowClients;
	}

	public void setIsShowClients(boolean isShowClients) {
		this.isShowClients = isShowClients;
	}

	public boolean getIsBlockTable() {
		return isBlockTable;
	}

	public void setIsBlockTable(boolean isBlockTable) {
		this.isBlockTable = isBlockTable;
	}

	public void show() {
		isShowClients = true;
	}

	public void hide() {
		isShowClients = false;
	}

	public void block() {
		isBlockTable = false;

	}

	public void unBlock() {
		isBlockTable = true;

	}

	public ArrayList<Client> getList() {
		return list;
	}

	public void setList(ArrayList<Client> list) {
		this.list = list;
	}

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
}
