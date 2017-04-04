package ua.com.foxminded.serviceacc.controller.client;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import ua.com.foxminded.serviceacc.model.Person;
import ua.com.foxminded.serviceacc.model.Client;
import ua.com.foxminded.serviceacc.model.ClientLevelType;
import ua.com.foxminded.serviceacc.model.ClientStatusType;
import ua.com.foxminded.serviceacc.service.ClientService;

@Named
public class ClientAddNew implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean isShowNewClientForm;
	private String firstName;
	private String lastName;

	@Inject
	private ClientService clientService;

	public void newClientFormOnSave() {
		Client addNewClient = new Client();
		Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setBirthday(new Date());
		addNewClient.setPerson(person);
		addNewClient.setStatus(new ClientStatusType("Active"));
		addNewClient.setLevel(new ClientLevelType("Applicant"));
		clientService.create(addNewClient);
		hide();
	}

	public void newClientFormOnCancel() {
		hide();
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}
