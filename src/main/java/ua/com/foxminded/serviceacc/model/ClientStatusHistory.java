package ua.com.foxminded.serviceacc.model;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ua.com.foxminded.serviceacc.model.ClientStatusType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "client_status_history")
public class ClientStatusHistory {
	
    @Id
    @SequenceGenerator (name = "generator", sequenceName = "clientStatusHistory_id_seq")
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "generator")
    @Column (name = "id", unique = true, nullable = false)
    private Long id;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "client_id")
	private Client client;

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "client_status_type_id")
	private ClientStatusType statusChanged;
	
	@Temporal(TemporalType.DATE)
	@Column (name = "date_changed")
	private Date dateChanged;

	public ClientStatusHistory() {
	}


    public ClientStatusHistory(Client client, ClientStatusType statusChanged, Date dateChanged) {
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

	public ClientStatusType getStatusChanged() {
		return statusChanged;
	}

	public void setStatusChanged(ClientStatusType statusChanged) {
		this.statusChanged = statusChanged;
	}

	public Date getDateChanged() {
		return dateChanged;
	}

	public void setDateChanged(Date dateChanged) {
		this.dateChanged = dateChanged;
	}

}