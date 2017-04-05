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

@Entity
@Table(name = "client")
public class Client {

	@Id
	@SequenceGenerator(name = "generator", sequenceName = "client_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id")
	private Person person;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manager_id")
	private Manager manager;

	// @Column(name = "level")
	// @Enumerated(EnumType.STRING)
	// private ClientLevel level;
	//
	// @Column(name = "status")
	// @Enumerated(EnumType.STRING)
	// private ClientStatus status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_level_type_id")
	private ClientLevelType level;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_status_type_id")
	private ClientStatusType status;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ClientStatusHistory> clientHistory = new HashSet<>();

	@Column(name = "active")
	private boolean active = true;

	public Client() {
	}

	// public Client(Person person, Manager manager, ClientLevel level,
	// ClientStatus status, Set<ClientStatusHistory> clientHistory) {
	// this.person = person;
	// this.manager = manager;
	// this.level = level;
	// this.status = status;
	// this.clientHistory = clientHistory;
	// }

	public Client(Person person, Manager manager, ClientLevelType level, ClientStatusType status,
			Set<ClientStatusHistory> clientHistory) {
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

	// public ClientLevel getLevel() {
	// return level;
	// }
	//
	// public void setLevel(ClientLevel level) {
	// this.level = level;
	// }
	//
	// public ClientStatus getStatus() {
	// return status;
	// }
	//
	// public void setStatus(ClientStatus status) {
	// this.status = status;
	// }

	public Set<ClientStatusHistory> getClientHistory() {
		return clientHistory;
	}

	public ClientLevelType getLevel() {
		return level;
	}

	public void setLevel(ClientLevelType level) {
		this.level = level;
	}

	public ClientStatusType getStatus() {
		return status;
	}

	public void setStatus(ClientStatusType status) {
		this.status = status;
	}

	public void setClientHistory(Set<ClientStatusHistory> clientHistory) {
		this.clientHistory = clientHistory;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}