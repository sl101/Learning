package ua.com.foxminded.serviceacc.controller.client;

import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import ua.com.foxminded.serviceacc.model.Client;
import ua.com.foxminded.serviceacc.model.ClientStatusType;
import ua.com.foxminded.serviceacc.model.Person;
import ua.com.foxminded.serviceacc.service.ClientService;
import ua.com.foxminded.serviceacc.service.ClientStatusTypeService;
import ua.com.foxminded.serviceacc.service.PersonService;

@Named
public class ClientUpdate {

	private boolean isShowUpdateForm;

	private Client client;

	private Person person;

	@Inject
	private ClientService clientService;

	@Inject
	private PersonService personService;

	@Inject
	private ClientStatusTypeService clientStatusTypeService;

	private ClientSelected clientSelected;

	private MenuModel model;

	public void init(ClientSelected clientSelected) {
		this.clientSelected = clientSelected;
		client = new Client();
		person = new Person();
		client.setPerson(person);
		client.setId(clientSelected.getSelectedClient().getId());
		client.setStatus(clientSelected.getSelectedClient().getStatus());

		person.setId(clientSelected.getSelectedClient().getPerson().getId());
		person.setFirstName(clientSelected.getSelectedClient().getPerson().getFirstName());
		person.setLastName(clientSelected.getSelectedClient().getPerson().getLastName());

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
		personService.update(person);
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
		return client.getPerson().getFirstName();
	}

	public void setFirstName(String firstName) {
		client.getPerson().setFirstName(firstName);
	}

	public String getLastName() {
		return client.getPerson().getLastName();
	}

	public void setLastName(String secondName) {
		client.getPerson().setLastName(secondName);
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