package ua.com.foxminded.serviceacc.controller.menu;

import javax.inject.Inject;
import javax.inject.Named;

import ua.com.foxminded.serviceacc.controller.client.ClientController;

@Named
public class Menu {

	@Inject
	private ClientController clientController;

	public void menuOnAdd() {
		clientController.blockTable();
		clientController.showNewClientForm();

	}

	public void menuOnAddComplete() {
		clientController.unBlockTable();
		clientController.allClientsUpdate();
		clientController.hideNewClientForm();

	}

	// public void menuOnAll() {
	// clientController.showAllClient();
	// }

	public ClientController getClientsAll() {
		return clientController;
	}

	public void setClientsAll(ClientController clientController) {
		this.clientController = clientController;
	}

}
