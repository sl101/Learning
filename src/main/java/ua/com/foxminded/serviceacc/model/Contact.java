package ua.com.foxminded.serviceacc.model;

import ua.com.foxminded.serviceacc.model.constants.ContactType;

public class Contact {
	private Long id;
	private Person person;
	private String contactDefinition;
	private ContactType contactType;

	public Contact() {

	}

	public Contact(Person person, String contactDefinition, ContactType contactType) {
		this.person = person;
		this.contactDefinition = contactDefinition;
		this.contactType = contactType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getContactDefinition() {
		return contactDefinition;
	}

	public void setContactDefinition(String contactDefinition) {
		this.contactDefinition = contactDefinition;
	}

	public ContactType getContactType() {
		return contactType;
	}

	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}

}
