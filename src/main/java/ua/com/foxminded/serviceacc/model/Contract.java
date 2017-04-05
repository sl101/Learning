package ua.com.foxminded.serviceacc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "contract")
public class Contract {

	@Id
	@SequenceGenerator(name = "generator", sequenceName = "contract_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "number")
	private String number;

	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	private Client client;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manager_id")
	private Manager manager;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_id")
	private Service service;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "money_id")
	private Money clientRate;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "money_id")
	private Money managerRate;

	@Column(name = "active")
	private boolean active = true;

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
