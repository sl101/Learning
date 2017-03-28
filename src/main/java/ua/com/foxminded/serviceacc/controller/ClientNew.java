package ua.com.foxminded.serviceacc.controller;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import ua.com.foxminded.serviceacc.model.domain.Client;
import ua.com.foxminded.serviceacc.service.ClientServices;

@Named
public class ClientNew implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean isShowNewClientForm;
	private String firstName;
	private String secondName;

	@Inject
	private ClientServices clientServices;

	@Inject
	private Clients clients;

	public void newClientFormOnSave() {
		Client addNewClient = new Client();
		addNewClient.setFirstName(firstName);
		addNewClient.setSecondName(secondName);
		clientServices.save(addNewClient);
		clients.menuOnAddComplete();
	}

	public void newClientFormOnCancel() {
		clients.menuOnAddComplete();
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
