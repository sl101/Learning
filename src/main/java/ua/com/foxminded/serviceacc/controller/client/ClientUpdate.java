package ua.com.foxminded.serviceacc.controller.client;

import javax.inject.Inject;
import javax.inject.Named;

import ua.com.foxminded.serviceacc.model.Client;
import ua.com.foxminded.serviceacc.model.Person;
import ua.com.foxminded.serviceacc.service.ClientService;
import ua.com.foxminded.serviceacc.service.PersonService;

@Named
public class ClientUpdate {

	private boolean isShowUpdateForm;

	private Client client;
	private Person person;

	@Inject
	private ClientService clientService;

	@Inject
	private PersonService personService;

	private ClientSelected clientSelected;

	public void init(ClientSelected clientSelected) {
		this.clientSelected = clientSelected;
		client = new Client();
		person = new Person();
		client.setPerson(person);
		client.setId(clientSelected.getSelectedClient().getId());
		person.setId(clientSelected.getSelectedClient().getPerson().getId());
		person.setFirstName(clientSelected.getSelectedClient().getPerson().getFirstName());
		person.setLastName(clientSelected.getSelectedClient().getPerson().getLastName());

	}

	public void hide() {
		setIsShowUpdateForm(false);
	}

	public void show() {
		setIsShowUpdateForm(true);
	}

	public void updateFormButtonOk() {
		personService.update(person);
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
		return client.getPerson().getFirstName();
	}

	public void setFirstName(String firstName) {
		client.getPerson().setFirstName(firstName);
	}

	public String getSecondName() {
		return client.getPerson().getLastName();
	}

	public void setSecondName(String secondName) {
		client.getPerson().setLastName(secondName);
	}

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	// public ClientSelected getClientSelected() {
	// return clientSelected;
	// }
	//
	// public void setClientSelected(ClientSelected clientSelected) {
	// this.clientSelected = clientSelected;
	// }

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
