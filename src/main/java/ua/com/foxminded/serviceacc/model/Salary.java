package ua.com.foxminded.serviceacc.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "salary")
public class Salary {
	
	@Id
	@SequenceGenerator(name = "generator", sequenceName = "salary_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "manager_id")
	private Manager manager;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "money_id")
	private Money amount;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "payStatus_id")
	private PayStatus status;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "salary", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Invoice> invoices = new HashSet<>();
	
	@Column(name = "active")
	private boolean active = true;

	public Salary() {

	}

	public Salary(Date date, Manager manager, Money amount, PayStatus status, Set<Invoice> invoices) {
		this.date = date;
		this.manager = manager;
		this.amount = amount;
		this.status = status;
		this.invoices = invoices;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Money getAmount() {
		return amount;
	}

	public void setAmount(Money amount) {
		this.amount = amount;
	}

	public PayStatus getStatus() {
		return status;
	}

	public void setStatus(PayStatus status) {
		this.status = status;
	}

	public Set<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}

}
