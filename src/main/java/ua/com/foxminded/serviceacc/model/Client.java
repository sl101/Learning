package ua.com.foxminded.serviceacc.model;

import ua.com.foxminded.serviceacc.model.constant.ClientStatus;
import ua.com.foxminded.serviceacc.model.constant.ClientLevel;

public class Client {

	private Long id;
	private Person person;
	private Manager manager;
	private ClientLevel level;
	private ClientStatus status;

	public Client() {
	}

    public Client(Person person, Manager manager, ClientLevel level, ClientStatus status) {
        this.person = person;
        this.manager = manager;
        this.level = level;
        this.status = status;
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

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public ClientLevel getLevel() {
        return level;
    }

    public void setLevel(ClientLevel level) {
        this.level = level;
    }

    public ClientStatus getStatus() {
        return status;
    }

    public void setStatus(ClientStatus status) {
        this.status = status;
    }
}