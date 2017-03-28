package ua.com.foxminded.serviceacc.controller;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import ua.com.foxminded.serviceacc.model.domain.Client;
import ua.com.foxminded.serviceacc.service.ClientServices;

@Named
public class ClientUpdate {
	private static final Logger log = Logger.getLogger("ClientUpdate : ");

	private Client selectedClient;

	private boolean isShowUpdateForm;

	@Inject
	private ClientServices clientServices;

	@Inject
	private Clients clients;

	public void hide() {
		setIsShowUpdateForm(false);
	}

	public void show() {
		log.info("UpdateForm.show()");
		setIsShowUpdateForm(true);
	}

	public Client getSelectedClient() {
		return selectedClient;
	}

	public void setSelectedClient(Client selectedClient) {
		log.info("UpdateForm.setSelectedClient()");
		this.selectedClient = selectedClient;

	}

	public void updateFormButtonOk() {
		log.info("UpdateForm.updateFormButtonOk()");
		clientServices.update(selectedClient);
		clients.selectedFormOnUpdateComplete();

	}

	public void updateFormButtonCancel() {
		log.info("UpdateForm.updateFormButtonCancel()");
		clients.selectedFormOnUpdateComplete();
	}

	public void updateFormChangeLevel(String level) {
		log.info("updateFormChangeLevel(" + level + ")");
		selectedClient.setLevelAsString(level);
	}

	public void updateFormChangeStatus(String status) {
		log.info("updateFormChangeStatus(" + status + ")");
		selectedClient.setStatusAsString(status);
	}

	public boolean getIsShowUpdateForm() {
		return isShowUpdateForm;
	}

	public void setIsShowUpdateForm(boolean isShowUpdateForm) {
		this.isShowUpdateForm = isShowUpdateForm;
	}

	public Clients getClients() {
		return clients;
	}

	public void setClients(Clients clients) {
		this.clients = clients;
	}

	public String getFirstName() {
		return selectedClient.getFirstName();
	}

	public void setFirstName(String firstName) {
		selectedClient.setFirstName(firstName);
	}

	public String getSecondName() {
		return selectedClient.getSecondName();
	}

	public void setSecondName(String secondName) {
		selectedClient.setSecondName(secondName);
	}
}
