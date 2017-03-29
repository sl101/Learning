package ua.com.foxminded.serviceacc.model;

import java.util.Date;

public class Contract {
	private Long id;
	private String number;
	private Date date;
	private Client client;
	private Manager manager;
	private Service service;
	private Money clientRate;
	private Money managerRate;

	public Contract() {

	}

	public Contract(String number, Date date, Client client, Manager manager, Service service, Money clientRate,
			Money managerRate) {
		this.number = number;
		this.date = date;
		this.client = client;
		this.manager = manager;
		this.service = service;
		this.clientRate = clientRate;
		this.managerRate = managerRate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Money getClientRate() {
		return clientRate;
	}

	public void setClientRate(Money clientRate) {
		this.clientRate = clientRate;
	}

	public Money getManagerRate() {
		return managerRate;
	}

	public void setManagerRate(Money managerRate) {
		this.managerRate = managerRate;
	}

}
