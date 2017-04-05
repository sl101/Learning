package ua.com.foxminded.serviceacc.model;

import java.util.Currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "money")
public class Money {
	
	@Id
	@SequenceGenerator(name = "generator", sequenceName = "money_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "currency")
	private Currency currency;
	
	@Column(name = "amount")
	private int amount;
	
	@Column(name = "active")
	private boolean active = true;

	public Money() {

	}

	public Money(Currency currency, int amount) {
		this.currency = currency;
		this.amount = amount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
