package ua.com.foxminded.serviceacc.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "contact_type")
public class ContactType {
	@Id
	@SequenceGenerator(name = "generator", sequenceName = "contact_type_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "code", unique = true, nullable = false)
	private String code;

	@Column(name = "contact_type", unique = true, nullable = false)
	private String contactType;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contact_type", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Contact> contacts = new HashSet<>();
	
	@Column(name = "active")
	private boolean active = true;
	
	public ContactType(){
		
	}

	public ContactType(String code, String contactType, Set<Contact> contacts, boolean active) {
		super();
		this.code = code;
		this.contactType = contactType;
		this.contacts = contacts;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	

}
