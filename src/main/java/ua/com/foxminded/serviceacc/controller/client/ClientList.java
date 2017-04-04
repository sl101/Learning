package ua.com.foxminded.serviceacc.controller.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.serviceacc.model.Client;
import ua.com.foxminded.serviceacc.service.ClientService;
import ua.com.foxminded.serviceacc.service.ClientServices;

@Named
public class ClientList implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Client> list;

	private boolean isShowClients = false;

	private boolean isBlockTable;

	@Inject
	private ClientService clientService;

	@PostConstruct
	public void init() {
		updateData();
	}

	public void updateData() {
		list = clientService.findAll();
	}

	public boolean getIsShowClients() {
		return isShowClients;
	}

	public void setIsShowClients(boolean isShowClients) {
		this.isShowClients = isShowClients;
	}

	public boolean getIsBlockTable() {
		return isBlockTable;
	}

	public void setIsBlockTable(boolean isBlockTable) {
		this.isBlockTable = isBlockTable;
	}

	public void show() {
		isShowClients = true;
	}

	public void hide() {
		isShowClients = false;
	}

	public void block() {
		isBlockTable = false;

	}

	public void unBlock() {
		isBlockTable = true;

	}
	@Transactional
	public List<Client> getList() {
		list = clientService.findAll();
		return list;
	}

	public void setList(List<Client> list) {
		this.list = list;
	}

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}
}
