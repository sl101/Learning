package ua.com.foxminded.serviceacc.model;

import ua.com.foxminded.serviceacc.model.enums.ClientStatus;
import ua.com.foxminded.serviceacc.model.enums.Level;

public class Client {
	private Person person;
	private Manager manager;
	private Level level;
	private ClientStatus status;
	
	public Client (){
		this.level = Level.Applicant;
	}
	
	public Level increaseLevel(){
		return this.level.up();	
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
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	public ClientStatus getStatus() {
		return status;
	}
	public void setStatus(ClientStatus status) {
		this.status = status;
	}
	
	
	
}