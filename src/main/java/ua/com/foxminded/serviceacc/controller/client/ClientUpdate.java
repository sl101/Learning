package ua.com.foxminded.serviceacc.controller.client;

import javax.inject.Inject;
import javax.inject.Named;

import ua.com.foxminded.serviceacc.model.Client;
import ua.com.foxminded.serviceacc.service.ClientService;

@Named
public class ClientUpdate {

	private boolean isShowUpdateForm;

	private Client client;

	@Inject
	private ClientService clientService;

	private ClientSelected clientSelected;

	public void init(ClientSelected clientSelected) {
		this.clientSelected = clientSelected;
		client = new Client();
		client.setId(clientSelected.getSelectedClient().getId());
		client.setFirstName(clientSelected.getSelectedClient().getFirstName());
		client.setLastName(clientSelected.getSelectedClient().getLastName());
	}

	public void hide() {
		setIsShowUpdateForm(false);
	}

	public void show() {
		setIsShowUpdateForm(true);
	}

	public void updateFormButtonOk() {
		clientService.update(client);
		clientSelected.selectedFormOnUpdateComplete();
	}

	public void updateFormButtonCancel() {
		hide();
	}

	public void updateFormChangeLevel(String level) {
		// client.setLevelAsString(level);
	}

	public void updateFormChangeStatus(String status) {
		// client.setStatusAsString(status);
	}

	public boolean getIsShowUpdateForm() {
		return isShowUpdateForm;
	}

	public void setIsShowUpdateForm(boolean isShowUpdateForm) {
		this.isShowUpdateForm = isShowUpdateForm;
	}

	public String getFirstName() {
		return client.getFirstName();
	}

	public void setFirstName(String firstName) {
		client.setFirstName(firstName);
	}

	public String getLastName() {
		return client.getLastName();
	}

	public void setLastName(String secondName) {
		client.setLastName(secondName);
	}

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
