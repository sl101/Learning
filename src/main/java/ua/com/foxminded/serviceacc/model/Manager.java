package ua.com.foxminded.serviceacc.model;

import java.util.HashSet;
import java.util.Set;

public class Manager {

	private Long id;
	private Person person;
	private Set<Client> clients = new HashSet<>();

	public Manager() {

	}

    public Manager(Person person, Set<Client> clients) {
        this.person = person;
        this.clients = clients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
}
