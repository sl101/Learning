package ua.com.foxminded.serviceacc.controller.client;

import javax.inject.Inject;
import javax.inject.Named;

import ua.com.foxminded.serviceacc.model.domain.Client;
import ua.com.foxminded.serviceacc.service.ClientServices;

@Named
public class ClientUpdate {

	private boolean isShowUpdateForm;

	private Client client;

	@Inject
	private ClientServices clientServices;

	@Inject
	private ClientSelected clientSelected;

	public void init(Client selectedClient) {
		client = new Client();
		client.setId(clientSelected.getSelectedClient().getId());
		client.setFirstName(clientSelected.getSelectedClient().getFirstName());
		client.setSecondName(clientSelected.getSelectedClient().getSecondName());
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
		clientServices.update(client);
		clientSelected.selectedFormOnUpdateComplete();
	}

	public void updateFormButtonCancel() {
		clientSelected.selectedFormOnUpdateComplete();
	}

	public void updateFormChangeLevel(String level) {
		client.setLevelAsString(level);
	}

	public void updateFormChangeStatus(String status) {
		client.setStatusAsString(status);
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

	public String getSecondName() {
		return client.getSecondName();
	}

	public void setSecondName(String secondName) {
		client.setSecondName(secondName);
	}

	public ClientServices getClientServices() {
		return clientServices;
	}

	public void setClientServices(ClientServices clientServices) {
		this.clientServices = clientServices;
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
