package ua.com.foxminded.serviceacc.model;

import javax.persistence.Column;

public class Service {
	private Long id;
	private String name;
	private Money price;
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
