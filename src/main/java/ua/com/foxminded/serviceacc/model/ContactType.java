package ua.com.foxminded.serviceacc.model;

import javax.persistence.*;

/**
 * Created by andreb on 04.04.17.
 * Represent contact type
 */
@Entity
@Table(name = "contact_type")
public class ContactType {
    @Id
    @SequenceGenerator(name = "generator", sequenceName = "client_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @Column (name = "id", unique = true, nullable = false)
    private Long id;
    @Column (name = "contact_type", unique = true, nullable = false)
    private String contactType;

    public ContactType() {
    }

    public ContactType(String contactType) {
        this.contactType = contactType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }
}
