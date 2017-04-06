package ua.com.foxminded.serviceacc.model;

import javax.persistence.*;

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

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id")
	private Person person;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "manager", orphanRemoval = true)
    private Set<Client> clients = new HashSet<>();

	@Column(name = "active")
	private boolean active = true;


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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}