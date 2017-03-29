package ua.com.foxminded.serviceacc.controller.client;

import javax.inject.Inject;
import javax.inject.Named;

import ua.com.foxminded.serviceacc.model.domain.Client;
import ua.com.foxminded.serviceacc.service.ClientServices;

@Named
public class ClientSelected {

	@Inject
	private ClientServices clientServices;

	@Inject
	private ClientsAll clientsAll;

	@Inject
	private ClientUpdate clientUpdate;

	private Client selectedClient;

	private boolean isShowSelectedClientForm;

	public void hide() {
		setIsShowSelectedClientForm(false);
	}

	public void show() {
		setIsShowSelectedClientForm(true);
	}

	public void selectedFormOnOk() {
		clientsAll.onRowSelectComplete();
	}

	public void selectedFormOnDelete() {
		clientServices.delete(selectedClient);
		clientsAll.allClientsUpdate();
		clientsAll.onRowSelectComplete();
	}

	public void selectedFormOnUpdate() {
		clientsAll.blockTable();
		clientsAll.hideMenuForm();
		hide();
		clientUpdate.init(selectedClient);
		clientUpdate.show();
		clientsAll.allClientsUpdate();
	}

	public void selectedFormOnUpdateComplete() {
		clientsAll.unBlockTable();
		clientsAll.allClientsUpdate();
		clientUpdate.hide();
		clientsAll.showMenuForm();
		selectedClient = null;

	}

	public ClientServices getClientServices() {
		return clientServices;
	}

	public void setClientServices(ClientServices clientServices) {
		this.clientServices = clientServices;
	}

	public ClientsAll getClients() {
		return clientsAll;
	}

	public void setClients(ClientsAll clientsAll) {
		this.clientsAll = clientsAll;
	}

	public boolean getIsShowSelectedClientForm() {
		return isShowSelectedClientForm;
	}

	public void setIsShowSelectedClientForm(boolean isShowSelectedClientForm) {
		this.isShowSelectedClientForm = isShowSelectedClientForm;
	}

	public Client getSelectedClient() {
		return selectedClient;
	}

	public void setSelectedClient(Client selectedClient) {
		this.selectedClient = selectedClient;
	}

	public ClientUpdate getClientUpdate() {
		return clientUpdate;
	}

	public void setClientUpdate(ClientUpdate clientUpdate) {
		this.clientUpdate = clientUpdate;
	}

}
