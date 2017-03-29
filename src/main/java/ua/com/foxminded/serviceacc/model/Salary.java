package ua.com.foxminded.serviceacc.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import ua.com.foxminded.serviceacc.model.constants.PayStatus;

public class Salary {
	private Long id;
	private Date date;
	private Manager manager;
	private Money amount;
	private PayStatus status;
	private Set<Invoice> invoices = new HashSet<>();

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
