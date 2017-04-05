package ua.com.foxminded.serviceacc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "service")
public class Service {
	
	@Id
	@SequenceGenerator(name = "generator", sequenceName = "client_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "active")
	private String name;
	
	@ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "money_id")
	private Money price;
	
	@ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "serviceType_id")
	private ServiceType type;
	
	@Column(name = "active")
	private boolean active = true;

	public Service() {

	}

	public Service(String name, Money price, ServiceType type) {
		this.name = name;
		this.price = price;
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Money getPrice() {
		return price;
	}

	public void setPrice(Money price) {
		this.price = price;
	}

	public ServiceType getType() {
		return type;
	}

	public void setType(ServiceType type) {
		this.type = type;
	}

}
