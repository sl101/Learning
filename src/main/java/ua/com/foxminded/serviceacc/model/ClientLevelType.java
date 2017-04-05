package ua.com.foxminded.serviceacc.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "client_level_type")
public class ClientLevelType {

	@Id
	@SequenceGenerator(name = "generator", sequenceName = "client_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "code", unique = true, nullable = false)
	private String code;

	@Column(name = "level", unique = true, nullable = false)
	private String level;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "level", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Client> clients = new HashSet<>();

	@Column(name = "active")
	private boolean active = true;

	public ClientLevelType() {

	}

	public ClientLevelType(String code, String level, Set<Client> clients) {
		this.code = code;
		this.level = level;
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

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
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
