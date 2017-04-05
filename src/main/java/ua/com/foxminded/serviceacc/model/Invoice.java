package ua.com.foxminded.serviceacc.model;

import java.util.Date;

import javax.persistence.Column;

public class Invoice {
	private Long id;
	private String number;
	private Date date;
	private Contract contract;
	private Money price;
	private PayStatus status;
	private Payment payment;
	
	@Column(name = "active")
	private boolean active = true;

	public Invoice() {

	}

	public Invoice(String number, Date date, Contract contract, Money price, PayStatus status, Payment payment) {
		this.number = number;
		this.date = date;
		this.contract = contract;
		this.price = price;
		this.status = status;
		this.payment = payment;
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

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public Money getPrice() {
		return price;
	}

	public void setPrice(Money price) {
		this.price = price;
	}

	public PayStatus getStatus() {
		return status;
	}

	public void setStatus(PayStatus status) {
		this.status = status;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

}
