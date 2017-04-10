package ua.com.foxminded.serviceacc.model;

import javax.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "manager")
public class Manager {

	@Id
	@SequenceGenerator(name = "generator", sequenceName = "manager_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Temporal(TemporalType.DATE)
	@Column(name = "birth_day")
	private Date birthday;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "manager", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Contact> contacts = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "manager", orphanRemoval = true)
    private Set<Client> clients = new HashSet<>();

	@Column(name = "active", nullable = false)
	private boolean active = true;


	public Manager() {

	}


	public Manager(String firstName, String lastName, Date birthday, Set<Contact> contacts, Set<Client> clients,
			boolean active) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.contacts = contacts;
		this.clients = clients;
		this.active = active;
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


	public Set<Client> getClients() {
		return clients;
	}


	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}

	

}