package ua.com.foxminded.serviceacc.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ua.com.foxminded.serviceacc.model.constant.ClientLevel;
import ua.com.foxminded.serviceacc.model.constant.ClientStatus;

@Entity
@Table(name = "client")
public class Client {

	@Id
	@SequenceGenerator(name = "generator", sequenceName = "client_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "person_id")
	private Person person;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "manager_id")
	private Manager manager;

	@Column(name = "level")
	@Enumerated(EnumType.STRING)
	private ClientLevel level;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private ClientStatus status;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ClientStatusHistory> clientHistory = new HashSet<>();

	public Client() {
	}

	public Client(Person person, Manager manager, ClientLevel level, ClientStatus status, Set<ClientStatusHistory> clientHistory) {
		this.person = person;
		this.manager = manager;
		this.level = level;
		this.status = status;
		this.clientHistory = clientHistory;
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

	public Set<ClientStatusHistory> getClientHistory() {
		return clientHistory;
	}

	public void setClientHistory(Set<ClientStatusHistory> clientHistory) {
		this.clientHistory = clientHistory;
	}

}