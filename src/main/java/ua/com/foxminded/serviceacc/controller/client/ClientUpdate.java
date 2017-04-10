package ua.com.foxminded.serviceacc.controller.client;

import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import ua.com.foxminded.serviceacc.model.Client;
import ua.com.foxminded.serviceacc.service.ClientService;
import ua.com.foxminded.serviceacc.model.ClientStatusType;
import ua.com.foxminded.serviceacc.service.ClientStatusTypeService;
@Named
public class ClientUpdate {

	private boolean isShowUpdateForm;

	private Client client;

	@Inject
	private ClientService clientService;

	@Inject
	private ClientStatusTypeService clientStatusTypeService;

	private ClientSelected clientSelected;

	private MenuModel model;

	public void init(ClientSelected clientSelected) {
		this.clientSelected = clientSelected;
		client = new Client();
		client.setId(clientSelected.getSelectedClient().getId());
		client.setFirstName(clientSelected.getSelectedClient().getFirstName());
		client.setLastName(clientSelected.getSelectedClient().getLastName());
		client.setStatus(clientSelected.getSelectedClient().getStatus());
		createStatusMenu();
	}

	
	private void createStatusMenu() {
		model = new DefaultMenuModel();
		DefaultSubMenu firstSubmenu = new DefaultSubMenu("choose status");		
		for (ClientStatusType  statusType: clientStatusTypeService.findAll()){
			DefaultMenuItem item = new DefaultMenuItem(statusType.getTitle());			
			firstSubmenu.addElement(item);
		}
		model.addElement(firstSubmenu);
	}	

	public void hide() {
		setIsShowUpdateForm(false);
	}

	public void show() {
		setIsShowUpdateForm(true);
	}

	public void updateFormButtonOk() {
		clientService.update(client);
		clientSelected.selectedFormOnUpdateComplete();
	}

	public void updateFormButtonCancel() {
		hide();
	}

	public void updateFormChangeLevel(String level) {
		// TODO: level
	}

	public void updateFormChangeStatus(String status) {		
		client.setStatus(clientStatusTypeService.findByStatusName(status));
	}

	public boolean getIsShowUpdateForm() {
		return isShowUpdateForm;
	}

	public void setIsShowUpdateForm(boolean isShowUpdateForm) {
		this.isShowUpdateForm = isShowUpdateForm;
	}

	public String getFirstName() {
		return client.getFirstName();
	}

	public void setFirstName(String firstName) {
		client.setFirstName(firstName);
	}

	public String getLastName() {
		return client.getLastName();
	}

	public void setLastName(String secondName) {
		client.setLastName(secondName);
	}

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public ClientStatusTypeService getClientStatusTypeService() {
		return clientStatusTypeService;
	}

	public void setClientStatusTypeService(ClientStatusTypeService clientStatusTypeService) {
		this.clientStatusTypeService = clientStatusTypeService;
	}

	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}
}