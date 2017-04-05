package ua.com.foxminded.serviceacc.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "client_status_type")
public class ClientStatusType {

	@Id
	@SequenceGenerator(name = "generator", sequenceName = "client_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "code", unique = true, nullable = false)
	private String code;

	@Column(name = "status", unique = true, nullable = false)
	private String status;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "status", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Client> clients = new HashSet<>();

	@Column(name = "active")
	private boolean active = true;

	public ClientStatusType() {

	}

	public ClientStatusType(String code, String status, Set<Client> clients) {
		this.code = code;
		this.status = status;
		this.clients = clients;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
