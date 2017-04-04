package ua.com.foxminded.serviceacc.model.constants;

import javax.persistence.*;

@Entity
@Table(name = "payment_type")
public class PaymentType {

	@Id
	@SequenceGenerator(name = "generator", sequenceName = "payment_type_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column (name = "id", unique = true, nullable = false)
	private Long id;

	@Column (name = "title")
	private String title;

	public PaymentType() {
	}

	public PaymentType(String title) {

		this.title = title;
	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
