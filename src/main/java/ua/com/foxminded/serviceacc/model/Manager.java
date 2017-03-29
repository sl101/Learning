package ua.com.foxminded.serviceacc.model;

import java.util.List;

public class Manager {
	private Person person;
	private List<Client> clients;
	
	public List<Client> addClient(Client client){
		clients.add(client);
		return clients;		
	}
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public List<Client> getClients() {
		return clients;
	}
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	
}
