package ua.com.foxminded.serviceacc.model;

import javax.persistence.*;

import ua.com.foxminded.serviceacc.model.ContactType;


@Entity
@Table(name = "contact")
public class Contact {

	@Id
	@SequenceGenerator(name = "generator", sequenceName = "contact_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id")
	private Person person;

	@Column(name = "contact_name")
	private String contactName;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "contact_type_id")
	private ContactType contactType;

	@Column(name = "active", nullable = false)
	private boolean active = true;

	public Contact() {
	}

	public Contact(Person person, String contact, ContactType contactType) {
		this.person = person;
		this.contactName = contact;
		this.contactType = contactType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contact) {
		this.contactName = contact;
	}

	public ContactType getContactType() {
		return contactType;
	}

	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}