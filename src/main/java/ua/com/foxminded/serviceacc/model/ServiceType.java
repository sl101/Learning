package ua.com.foxminded.serviceacc.model;

import javax.persistence.*;

@Entity
@Table(name = "service_type")
public class ServiceType {
	@Id
	@SequenceGenerator(name = "generator", sequenceName = "service_type_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "code", unique = true, nullable = false)
	private String code;
	
	@Column (name = "title", nullable = false)
	private String title;
	
	@Column(name = "active", nullable = false)
	private boolean active = true;

	public ServiceType() {
	}

	public ServiceType(String code, String title) {

		this.title = title;
	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
