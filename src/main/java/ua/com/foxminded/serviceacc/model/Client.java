package ua.com.foxminded.serviceacc.model;

import ua.com.foxminded.serviceacc.model.constant.ClientLevel;
import ua.com.foxminded.serviceacc.model.constant.ClientStatus;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "client")
public class Client {

    @Id
    @SequenceGenerator (name = "generator", sequenceName = "client_id_seq")
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "generator")
    @Column (name = "id", unique = true, nullable = false)
	private Long id;
	
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn (name = "person_id")
	private Person person;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn (name = "manager_id")
	private Manager manager;
    
    @Column (name = "level")
    @Enumerated (EnumType.STRING)
	private ClientLevel level;
    
    @Column (name = "status")
    @Enumerated (EnumType.STRING)
	private ClientStatus status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ClientStatusHistory> clientHistory = new HashSet<>();

	public Client() {
	}

    public Client(Person person, Manager manager, ClientLevel level, ClientStatus status, Set<ClientStatusHistory> clientHistory) {
        this.person = person;
        this.manager = manager;
        this.level = level;
        this.status = status;
        this.clientHistory = clientHistory;
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

    public Set<ClientStatusHistory> getClientHistory() {
        return clientHistory;
    }

    public void setClientHistory(Set<ClientStatusHistory> clientHistory) {
        this.clientHistory = clientHistory;
    }
}