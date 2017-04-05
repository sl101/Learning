package ua.com.foxminded.serviceacc.model;

import java.util.Date;

import javax.persistence.Column;

public class Payment {
	private Long id;
	private Date date;
	private Money money;
	private PaymentType type;

	@Column(name = "active")
	private boolean active = true;
	
	public Payment() {

	}

	public Payment(Date date, Money money, PaymentType type) {
		this.date = date;
		this.money = money;
		this.type = type;
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

	public Money getMoney() {
		return money;
	}

	public void setMoney(Money money) {
		this.money = money;
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}

}
