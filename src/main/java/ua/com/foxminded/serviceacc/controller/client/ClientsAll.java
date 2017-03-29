package ua.com.foxminded.serviceacc.controller.client;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import ua.com.foxminded.serviceacc.model.domain.Client;
import ua.com.foxminded.serviceacc.service.ClientServices;

@Named
public class ClientsAll implements Serializable {

	private static final long serialVersionUID = 1L;

	private ArrayList<Client> list;

	private boolean isBlockMenuAdd;

	private boolean isShowClients = false;

	public boolean getIsShowClients() {
		return isShowClients;
	}

	public void setIsShowClients(boolean isShowClients) {
		this.isShowClients = isShowClients;
	}

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
		list = clientServices.getAll();
	}

	public void onRowSelect(SelectEvent event) {
		showMenuForm();
		clientSelected.show();
	}

	public void onRowSelectComplete() {
		clientSelected.hide();
		hideMenuForm();
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

	public void menuOnAll() {
		isShowClients = true;
	}

	public void menuOnMain() {
		isShowClients = false;
	}

	public void hideMenuForm() {
		setIsBlockMenuAdd(false);
	}

	public void showMenuForm() {
		setIsBlockMenuAdd(true);
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

	public boolean getIsBlockMenuAdd() {
		return isBlockMenuAdd;
	}

	public void setIsBlockMenuAdd(boolean isShowMenuForm) {
		this.isBlockMenuAdd = isShowMenuForm;
	}

	public ArrayList<Client> getList() {
		return list;
	}

	public void setList(ArrayList<Client> clientsAll) {
		this.list = clientsAll;
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
