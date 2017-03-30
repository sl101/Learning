package ua.com.foxminded.serviceacc.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name = "managers")
public class Manager {
	
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id", unique = true, nullable = false)
	private Long id;
    
    @OneToOne
    @JoinColumn (name = "person_id")
	private Person person;
    
	@OneToMany(mappedBy = "manager", cascade=CascadeType.ALL,orphanRemoval=true)
    private Set<Client> clients = new HashSet<>();

	public Manager() {

	}

    public Manager(Person person, Set<Client> clients) {
        this.person = person;
        this.clients = clients;
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

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
}
