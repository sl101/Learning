package ua.com.foxminded.serviceacc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ua.com.foxminded.serviceacc.model.constant.ClientStatus;

@Entity
@Table (name = "client_status_history")
public class ClientStatusHistory {
	
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id", unique = true, nullable = false)
    private Long id;
    
    @OneToOne
    @JoinColumn (name = "client_id")
	private Client client;
    
    @Column (name = "status_changed")
    @Enumerated (EnumType.ORDINAL)
	private ClientStatus statusChanged;
	
	@Temporal(TemporalType.DATE)
	@Column (name = "date_changed")
	private Date dateChanged;

	public ClientStatusHistory() {

	}

    public ClientStatusHistory(Client client, ClientStatus statusChanged, Date dateChanged) {
        this.client = client;
        this.statusChanged = statusChanged;
        this.dateChanged = dateChanged;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public ClientStatus getStatusChanged() {
		return statusChanged;
	}

	public void setStatusChanged(ClientStatus statusChanged) {
		this.statusChanged = statusChanged;
	}

	public Date getDateChanged() {
		return dateChanged;
	}

	public void setDateChanged(Date dateChanged) {
		this.dateChanged = dateChanged;
	}

}
