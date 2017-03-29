package ua.com.foxminded.serviceacc.model;

import java.util.Date;

import ua.com.foxminded.serviceacc.model.constants.ClientStatus;

public class ClientStatusHistory {
	private Long id;
	private Client client;
	private ClientStatus statusChanged;
	private Date dateChanged;

	public ClientStatusHistory() {

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
