package ua.com.foxminded.serviceacc.controller;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import ua.com.foxminded.serviceacc.model.domain.Client;
import ua.com.foxminded.serviceacc.service.ClientServices;

@Named
public class ClientSelected {

	private static final Logger log = Logger.getLogger("ClientSelected : ");

	@Inject
	private ClientServices clientServices;

	@Inject
	private Clients clients;

	@Inject
	private ClientUpdate clientUpdate;

	private Client selectedClient;

	private boolean isShowSelectedClientForm;

	public void hide() {
		setIsShowSelectedClientForm(false);
	}

	public void show() {
		setIsShowSelectedClientForm(true);
	}

	public void selectedFormOnOk() {
		log.info(getMethodName());
		clients.onRowSelectComplete();
	}

	public void selectedFormOnDelete() {
		log.info(getMethodName());
		clientServices.delete(selectedClient);
		clients.allClientsUpdate();
		clients.onRowSelectComplete();
	}

	public void selectedFormOnUpdate() {
		log.info(getMethodName());
		clients.blockTable();
		clients.hideMenuForm();
		hide();
		clientUpdate.init(selectedClient);
		clientUpdate.show();
		clients.allClientsUpdate();
	}

	public void selectedFormOnUpdateComplete() {
		log.info(getMethodName());
		clients.unBlockTable();
		clients.allClientsUpdate();
		clientUpdate.hide();
		clients.showMenuForm();
		selectedClient = null;

	}

	public ClientServices getClientServices() {
		return clientServices;
	}

	public void setClientServices(ClientServices clientServices) {
		this.clientServices = clientServices;
	}

	public Clients getClients() {
		return clients;
	}

	public void setClients(Clients clients) {
		this.clients = clients;
	}

	public boolean getIsShowSelectedClientForm() {
		return isShowSelectedClientForm;
	}

	public void setIsShowSelectedClientForm(boolean isShowSelectedClientForm) {
		this.isShowSelectedClientForm = isShowSelectedClientForm;
	}

	private String getMethodName() {
		return " " + Thread.currentThread().getStackTrace()[2].getMethodName() + "() ";
	}

	public Client getSelectedClient() {
		return selectedClient;
	}

	public void setSelectedClient(Client selectedClient) {
		this.selectedClient = selectedClient;
	}

	public ClientUpdate getClientUpdate() {
		return clientUpdate;
	}

	public void setClientUpdate(ClientUpdate clientUpdate) {
		this.clientUpdate = clientUpdate;
	}

}
