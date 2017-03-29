package ua.com.foxminded.serviceacc.model;

import java.util.Currency;

public class Money {
	private Long id;
	private Currency currency;
	private int amount;

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
