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

	@Inject
	private ClientSelected clientSelected;

	public void init(Client selectedClient) {
		client = new Client();
		client.setId(clientSelected.getSelectedClient().getId());
		client.getPerson().setFirstName(clientSelected.getSelectedClient().getPerson().getFirstName());
		client.getPerson().setLastName(clientSelected.getSelectedClient().getPerson().getLastName());
		client.setLevel(clientSelected.getSelectedClient().getLevel());
		client.setStatus(clientSelected.getSelectedClient().getStatus());
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

//	public void updateFormChangeLevel(String level) {
//		client.setLevelAsString(level);
//	}
//
//	public void updateFormChangeStatus(String status) {
//		client.setStatusAsString(status);
//	}

	public boolean getIsShowUpdateForm() {
		return isShowUpdateForm;
	}

	public void setIsShowUpdateForm(boolean isShowUpdateForm) {
		this.isShowUpdateForm = isShowUpdateForm;
	}

	public String getFirstName() {
		return client.getPerson().getFirstName();
	}

	public void setFirstName(String firstName) {
		client.getPerson().setFirstName(firstName);
	}

	public String getLastName() {
		return client.getPerson().getLastName();
	}

	public void setLastName(String secondName) {
		client.getPerson().setLastName(secondName);
	}

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public ClientSelected getClientSelected() {
		return clientSelected;
	}

	public void setClientSelected(ClientSelected clientSelected) {
		this.clientSelected = clientSelected;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
