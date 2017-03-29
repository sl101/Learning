package ua.com.foxminded.serviceacc.model;

import ua.com.foxminded.serviceacc.model.constant.ContactType;

public class Contact {

	private Long id;
	private Person person;
	private String contact;
	private ContactType contactType;

	public Contact() {
	}

    public Contact(Person person, String contact, ContactType contactType) {
        this.person = person;
        this.contact = contact;
        this.contactType = contactType;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }
}
