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

	private boolean isShowMenuForm = true;

	private boolean isBlockTable;

	@Inject
	private ClientServices clientServices;

	@Inject
	private ClientNew clientNew;

	@Inject
	private ClientSelected clientSelected;

	@PostConstruct
	public void init() {
		allClientsUpdate();
	}

	public void allClientsUpdate() {
		allClients = clientServices.getAll();
	}

	public void onRowSelect(SelectEvent event) {
		log.info(getMethodName() + " id = " + ((Client) event.getObject()).getId());
		hideMenuForm();
		clientSelected.show();
	}

	public void onRowSelectComplete() {
		log.info(getMethodName());
		clientSelected.hide();
		showMenuForm();
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

	public ClientServices getClientServices() {
		return clientServices;
	}

	public void setClientServices(ClientServices clientServices) {
		this.clientServices = clientServices;
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

	public ClientSelected getClientSelected() {
		return clientSelected;
	}

	public void setClientSelected(ClientSelected clientSelected) {
		this.clientSelected = clientSelected;
	}

}
