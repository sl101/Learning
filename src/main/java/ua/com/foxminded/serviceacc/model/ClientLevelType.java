package ua.com.foxminded.serviceacc.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by andreb on 04.04.17. Class represent client graduate level
 */
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "level", orphanRemoval = true)
	private Set<Client> clients = new HashSet<>();

	@Column(name = "active")
	private boolean active = true;

	public ClientLevelType() {
	}

	public ClientLevelType(String code, String level) {
		this.code = code;
		this.level = level;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
