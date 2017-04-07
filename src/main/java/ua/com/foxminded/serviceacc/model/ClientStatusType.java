package ua.com.foxminded.serviceacc.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by andreb on 04.04.17.
 * Represent Client status
 */
@Entity
@Table(name = "client_status_type")
public class ClientStatusType {
    
	@Id
    @SequenceGenerator(name = "generator", sequenceName = "client_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
	
	@Column(name = "code", unique = true, nullable = false)
	private String code;
	
    @Column (name = "status", nullable = false)
    private String status;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "status", orphanRemoval = true)
    private Set<Client> clients = new HashSet<>();
    
    @Column(name = "active", nullable = false)
	private boolean active = true;


    public ClientStatusType() {
    }

    public ClientStatusType(String code, String status) {
        this.status = status;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
