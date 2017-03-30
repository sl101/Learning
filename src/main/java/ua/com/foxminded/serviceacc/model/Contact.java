package ua.com.foxminded.serviceacc.model;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ua.com.foxminded.serviceacc.model.constant.ContactType;

@Entity
@Table (name = "contacts")
public class Contact {
	
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id", unique = true, nullable = false)
	private Long id;
    
    @ManyToOne
    @JoinColumn (name = "person_id")
	private Person person;
    
    @Column (name = "contact_definition")
	private String contactDefinition;
    
    @Column (name = "contact_type")
    @Enumerated (EnumType.ORDINAL)
	private ContactType contactType;

	public Contact() {
	}

    public Contact(Person person, String contact, ContactType contactType) {
        this.person = person;
        this.contactDefinition = contact;
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

    public String getContactDefinition() {
        return contactDefinition;
    }

    public void setContactDefinition(String contact) {
        this.contactDefinition = contact;
    }
    
    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }
}
