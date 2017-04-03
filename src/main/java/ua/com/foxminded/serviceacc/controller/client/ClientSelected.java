package ua.com.foxminded.serviceacc.controller.client;

import javax.inject.Inject;
import javax.inject.Named;

import ua.com.foxminded.serviceacc.model.Client;
import ua.com.foxminded.serviceacc.service.ClientService;

@Named
public class ClientSelected {

	@Inject
	private ClientService clientService;

	@Inject
	private ClientController clientController;

	@Inject
	private ClientUpdate clientUpdate;

	private Client selectedClient;

	private boolean isShowSelectedClientForm;

	public void hide() {
		clientUpdate.hide();
		setIsShowSelectedClientForm(false);
	}

	public void show() {
		clientUpdate.hide();
		setIsShowSelectedClientForm(true);
	}

	public void selectedFormOnOk() {
		hide();
	}

	public void selectedFormOnDelete() {
		clientService.delete(selectedClient.getId());
		clientController.allClientsUpdate();
		hide();
	}

	public void selectedFormOnUpdate() {
		hide();
		clientController.blockTable();
		clientUpdate.init(this);
		clientUpdate.show();
		clientController.allClientsUpdate();
	}

	public void selectedFormOnUpdateComplete() {
		clientController.unBlockTable();
		clientController.allClientsUpdate();
		clientUpdate.hide();
		selectedClient = null;
	}

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public ClientController getClients() {
		return clientController;
	}

	public void setClients(ClientController clientController) {
		this.clientController = clientController;
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
