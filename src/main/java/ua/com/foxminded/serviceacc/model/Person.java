package ua.com.foxminded.serviceacc.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name = "person")
public class Person {
	
    @Id
    @SequenceGenerator (name = "generator", sequenceName = "person_id_seq")
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "generator")
    @Column (name = "id", unique = true, nullable = false)
    private Long id;
    
	@Column (name = "first_name")
	private String firstName;
	
	@Column (name = "last_name")
	private String lastName;
	
	@Temporal(TemporalType.DATE)
	@Column (name = "birth_day")
	private Date birthday;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Contact> contacts = new HashSet<>();

	public Person() {

	}

	public Person(String firstName, String lastName, Date birthday, Set<Contact> contacts) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
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
	
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

}