package ua.com.foxminded.serviceacc.controller.client;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ClientController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClientList clientList;

	@Inject
	private ClientAddNew clientAddNew;

	@Inject
	private ClientSelected clientSelected;

	public void allClientsUpdate() {
		clientList.updateData();
	}

	public void onRowSelect() {
		clientAddNew.hide();
		clientSelected.show();
	}

	public void menuOnMain() {
		clientList.hide();
	}

	public void showAllClient() {
		clientList.show();
	}

	public void hideAllClient() {
		clientList.hide();
	}

	public void showNewClientForm() {
		clientSelected.hide();
		clientAddNew.show();
	}

	public void hideNewClientForm() {
		clientAddNew.hide();
	}

	public ClientAddNew getNewClient() {
		return clientAddNew;
	}

	public void setNewClient(ClientAddNew clientAddNew) {
		this.clientAddNew = clientAddNew;
	}

	public ClientSelected getClientSelected() {
		return clientSelected;
	}

	public void setClientSelected(ClientSelected clientSelected) {
		this.clientSelected = clientSelected;
	}

}
