package ua.com.foxminded.serviceacc.model;

import javax.persistence.*;

import ua.com.foxminded.serviceacc.model.constant.ClientStatus;
import ua.com.foxminded.serviceacc.model.constant.ClientLevel;

@Entity
@Table (name = "clients")
public class Client {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id", unique = true, nullable = false)
	private Long id;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "person_id")
	private Person person;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "manager_id")
	private Manager manager;
    
    @Column (name = "level")
    @Enumerated (EnumType.ORDINAL)
	private ClientLevel level;
    
    @Column (name = "status")
    @Enumerated (EnumType.ORDINAL)
	private ClientStatus status;

	public Client() {
	}

    public Client(Person person, Manager manager, ClientLevel level, ClientStatus status) {
        this.person = person;
        this.manager = manager;
        this.level = level;
        this.status = status;
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

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public ClientLevel getLevel() {
        return level;
    }

    public void setLevel(ClientLevel level) {
        this.level = level;
    }

    public ClientStatus getStatus() {
        return status;
    }

    public void setStatus(ClientStatus status) {
        this.status = status;
    }
}