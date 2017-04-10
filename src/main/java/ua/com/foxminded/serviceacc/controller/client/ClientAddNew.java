package ua.com.foxminded.serviceacc.controller.client;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import ua.com.foxminded.serviceacc.model.Client;
import ua.com.foxminded.serviceacc.service.ClientService;
import ua.com.foxminded.serviceacc.service.ClientStatusTypeService;

@Named
public class ClientAddNew implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean isShowNewClientForm;
	private String firstName;
	private String secondName;

	@Inject
	private ClientService clientService;

	@Inject
	private ClientController clientController;
	
	@Inject
	private ClientStatusTypeService clientStatusTypeService;	
	
	public void newClientFormOnSave() {

		Client addNewClient = new Client();
		addNewClient.setFirstName(firstName);
		addNewClient.setLastName(secondName);
		addNewClient.setStatus(clientStatusTypeService.findById(1L));
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
	
	public ClientStatusTypeService getClientStatusTypeService() {
		return clientStatusTypeService;
	}

	public void setClientStatusTypeService(ClientStatusTypeService clientStatusTypeService) {
		this.clientStatusTypeService = clientStatusTypeService;
	}

}
