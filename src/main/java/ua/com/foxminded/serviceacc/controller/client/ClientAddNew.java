package ua.com.foxminded.serviceacc.controller.client;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import ua.com.foxminded.serviceacc.model.Client;
import ua.com.foxminded.serviceacc.model.Person;
import ua.com.foxminded.serviceacc.service.ClientService;
import ua.com.foxminded.serviceacc.service.PersonService;

@Named
public class ClientAddNew implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean isShowNewClientForm;
	private String firstName;
	private String secondName;

	@Inject
	private ClientService clientService;

	@Inject
	private PersonService personService;

	@Inject
	private ClientController clientController;

	public void newClientFormOnSave() {
		Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(secondName);
		Client addNewClient = new Client();
		addNewClient.setPerson(personService.create(person));
		clientService.create(addNewClient);
		clientController.allClientsUpdate();
		exit();
	}

	public void newClientFormOnCancel() {
		exit();
	}

	private void exit() {
		cleanInputTextFiled();
		hide();
	}

	private void cleanInputTextFiled() {
		firstName = "";
		secondName = "";
	}

	public boolean getIsShowNewClientForm() {
		return isShowNewClientForm;
	}

	public void setIsShowNewClientForm(boolean isShowNewClientForm) {
		this.isShowNewClientForm = isShowNewClientForm;
	}

	public void hide() {
		setIsShowNewClientForm(false);
	}

	public void show() {
		setIsShowNewClientForm(true);
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}
