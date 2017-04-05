package ua.com.foxminded.serviceacc.controller.menu;

import javax.inject.Inject;
import javax.inject.Named;

import ua.com.foxminded.serviceacc.controller.client.ClientController;

@Named
public class Menu {

	@Inject
	private ClientController clientController;

	public void menuOnAdd() {
		clientController.showNewClientForm();
	}

	public void menuOnAddComplete() {
		clientController.allClientsUpdate();
		clientController.hideNewClientForm();
	}

	public ClientController getController() {
		return clientController;
	}

	public void setClientController(ClientController clientController) {
		this.clientController = clientController;
	}

}
