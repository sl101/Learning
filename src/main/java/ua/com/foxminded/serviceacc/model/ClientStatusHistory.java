package ua.com.foxminded.serviceacc.model;

import ua.com.foxminded.serviceacc.model.constant.ClientStatus;

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
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "client_id")
	private Client client;
    
    @Column (name = "status_changed")
    @Enumerated (EnumType.STRING)
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