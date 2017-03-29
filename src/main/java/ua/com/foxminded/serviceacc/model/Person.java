package ua.com.foxminded.serviceacc.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Person {
	private Long id;
	private String firstName;
	private String lastName;
	private Date birthDay;
	private Set<Contact> contacts = new HashSet<>();

	public Person() {

	}

	public Person(String firstName, String lastName, Date birthDay, Set<Contact> contacts) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDay = birthDay;
		this.contacts = contacts;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

}
