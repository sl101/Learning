package ua.com.foxminded.serviceacc.controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;

import ua.com.foxminded.serviceacc.model.domain.Client;
import ua.com.foxminded.serviceacc.service.ClientServices;

@Named
public class Clients implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger("Clients : ");
	private ArrayList<Client> allClients;

	private Client selectedClient;

	private boolean isShowSelectedClientForm;

	private boolean isShowMenuForm = true;

	private boolean isBlockTable;

	@Inject
	private ClientServices clientServices;

	@Inject
	private ClientNew clientNew;

	@Inject
	private ClientUpdate clientUpdate;

	@PostConstruct
	public void init() {
		allClientsUpdate();
	}

	public void allClientsUpdate() {
		allClients = clientServices.getAll();
	}

	public void onRowSelect(SelectEvent event) {
		log.info(getMethodName() + " id = " + ((Client) event.getObject()).getId());
		selectedClient = (Client) event.getObject();
		showSelectedClientForm();
	}

	public void delete() {
		log.info(getMethodName());
		clientServices.delete(selectedClient);
	}

	public void menuOnAdd() {
		blockTable();
		clientNew.show();
		hideMenuForm();
	}

	public void menuOnAddComplete() {
		unBlockTable();
		allClientsUpdate();
		clientNew.hide();
		showMenuForm();

	}

	public void selectedFormOnOk() {
		log.info(getMethodName());
		hideSelectedClientForm();
		showMenuForm();
	}

	public void selectedFormOnDelete() {
		log.info(getMethodName());
		delete();
		hideSelectedClientForm();
		showMenuForm();
		allClientsUpdate();
	}

	public void selectedFormOnUpdate() {
		log.info(getMethodName());
		blockTable();
		hideSelectedClientForm();
		clientUpdate.setSelectedClient(selectedClient);
		clientUpdate.show();
		allClientsUpdate();
	}

	public void selectedFormOnUpdateComplete() {
		log.info(getMethodName());
		unBlockTable();
		allClientsUpdate();
		clientUpdate.hide();
		showMenuForm();
		selectedClient = null;

	}

	public void hideSelectedClientForm() {
		log.info(getMethodName());
		this.isShowSelectedClientForm = false;
	}

	public void showSelectedClientForm() {
		log.info(getMethodName());
		this.isShowSelectedClientForm = true;
	}

	public void hideMenuForm() {
		log.info(getMethodName());
		this.isShowMenuForm = false;
	}

	public void showMenuForm() {
		log.info(getMethodName());
		this.isShowMenuForm = true;
	}

	public void blockTable() {
		isBlockTable = true;
	}

	public void unBlockTable() {
		isBlockTable = false;
	}

	public String getIsUpdate() {
		if (selectedClient == null) {
			return "true";
		} else
			return "false";
	}

	public ClientServices getClientServices() {
		return clientServices;
	}

	public void setClientServices(ClientServices clientServices) {
		this.clientServices = clientServices;
	}

	public Client getSelectedClient() {
		return selectedClient;
	}

	public boolean getIsShowSelectedClientForm() {
		return isShowSelectedClientForm;
	}

	public void setIsShowSelectedClientForm(boolean isShowSelectedClientForm) {
		this.isShowSelectedClientForm = isShowSelectedClientForm;
	}

	public void setSelectedClient(Client selectedClient) {
		this.selectedClient = selectedClient;
	}

	public boolean getIsShowMenuForm() {
		return isShowMenuForm;
	}

	public void setIsShowMenuForm(boolean isShowMenuForm) {
		this.isShowMenuForm = isShowMenuForm;
	}

	private String getMethodName() {
		return " " + Thread.currentThread().getStackTrace()[2].getMethodName() + "() ";
	}

	public ArrayList<Client> getAllClients() {
		return allClients;
	}

	public void setAllClients(ArrayList<Client> allClients) {
		this.allClients = allClients;
	}

	public ClientNew getNewClient() {
		return clientNew;
	}

	public void setNewClient(ClientNew clientNew) {
		this.clientNew = clientNew;
	}

	public boolean getIsBlockTable() {
		return isBlockTable;
	}

	public void setIsBlockTable(boolean isBlockTable) {
		this.isBlockTable = isBlockTable;
	}

	public ClientUpdate getUpdateClient() {
		return clientUpdate;
	}

	public void setUpdateClient(ClientUpdate clientUpdate) {
		this.clientUpdate = clientUpdate;
	}

}
