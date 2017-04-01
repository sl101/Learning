package ua.com.foxminded.serviceacc.model;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ua.com.foxminded.serviceacc.model.constant.ContactType;

import javax.persistence.*;

@Entity
@Table (name = "contact")
public class Contact {

    @Id
    @SequenceGenerator (name = "generator", sequenceName = "contact_id_seq")
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "generator")
    @Column (name = "id", unique = true, nullable = false)
	private Long id;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "person_id")
	private Person person;
    
    @Column (name = "contact_name")
	private String contactName;
    
    @Column (name = "contact_type")
    @Enumerated (EnumType.STRING)
	private ContactType contactType;

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
}