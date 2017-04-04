package ua.com.foxminded.serviceacc.model.constants;

import javax.persistence.*;

@Entity
@Table(name = "service_type")
public class ServiceType {
	@Id
	@SequenceGenerator(name = "generator", sequenceName = "service_type_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column (name = "title")
	private String title;

	public ServiceType() {
	}

	public ServiceType(String title) {

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
}
