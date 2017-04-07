package ua.com.foxminded.serviceacc.model;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "invoice")
public class Invoice {
	
	@Id
	@SequenceGenerator(name = "generator", sequenceName = "invoice_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "number")
	private String number;

	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;
	
	@ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "contract_id")
	private Contract contract;
	
	@ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "money_id")
	private Money price;
	
	@ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "payStatus_id")
	private PayStatus status;
	
	@ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "payment_id")
	private Payment payment;
	
	@Column(name = "active", nullable = false)
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
