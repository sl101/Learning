package ua.com.foxminded.serviceacc.model;

import java.util.Calendar;

import ua.com.foxminded.serviceacc.model.enums.ClientStatus;

public class ClientStatusHistory {
	private Long id;
	private Client client;
	private ClientStatus statusChanged;
	private Calendar dateChanged;
	
	
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
	public Calendar getDateChanged() {
		return dateChanged;
	}
	public void setDateChanged(Calendar dateChanged) {
		this.dateChanged = dateChanged;
	}
	
}
