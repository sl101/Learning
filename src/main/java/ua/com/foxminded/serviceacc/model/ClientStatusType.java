package ua.com.foxminded.serviceacc.model;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Created by andreb on 04.04.17. Represent Client status
 */
@Entity
@Table(name = "client_status_type")
public class ClientStatusType {

	@Id
	@SequenceGenerator(name = "generator", sequenceName = "client_status_type_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "code", unique = true, nullable = false)
	private String code;

    @Column (name = "title", nullable = false)
    private String title;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "status", orphanRemoval = true)
    private Set<Client> clients = new HashSet<>();
    
    @Column(name = "active", nullable = false)
	private boolean active = true;

    public ClientStatusType() {
    }

    public ClientStatusType(String code, String title) {
        this.title = title;
        this.code = code;
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
