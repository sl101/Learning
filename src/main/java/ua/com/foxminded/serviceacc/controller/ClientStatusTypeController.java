package ua.com.foxminded.serviceacc.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ua.com.foxminded.serviceacc.controller.client.ClientController;
import ua.com.foxminded.serviceacc.model.ClientStatusType;
import ua.com.foxminded.serviceacc.service.ClientStatusTypeService;

@Named
@SessionScoped
@ManagedBean(name = "ClientStatusTypeController")
public class ClientStatusTypeController implements Serializable {
	private static final long serialVersionUID = 1L;
	private String code;
	private String title;
	private String id;

	private static boolean isShowForm = false;

	public boolean getIsShowForm() {
		return isShowForm;
	}

	public void setIsShowForm(boolean isShowForm) {
		this.isShowForm = isShowForm;
	}

	@Inject
	@ManagedProperty(value = "#{clientStatusTypeService}")
	ClientStatusTypeService clientStatusTypeService;

	@Inject
	@ManagedProperty(value = "#{clientController}")
	ClientController clientController;

	private List<ClientStatusType> statusList;

	@PostConstruct
	public void init() {
		clientStatusTypeService.findAll();
	}

	public List<ClientStatusType> getStatusList() {
		return clientStatusTypeService.findAll();
	}

	public void save() {
		if ("".equals(id)) {
			ClientStatusType newStatus = new ClientStatusType(code, title);
			clientStatusTypeService.save(newStatus);
		} else {
			ClientStatusType status = clientStatusTypeService.findById(Long.parseLong(id));
			status.setCode(code);
			status.setTitle(title);
			clientStatusTypeService.update(status);
		}
		id = "";
		code = "";
		title = "";
	}

	public void edit(ClientStatusType status) {
		code = status.getCode();
		title = status.getTitle();
		id = status.getId().toString();
	}

	public void delete(ClientStatusType status) {
		clientStatusTypeService.delete(status.getId());
	}

	public void showForm() {
		setIsShowForm(true);
		clientController.hideAllClient();
		clientStatusTypeService.findAll();
	}

	public void hideForm() {
		setIsShowForm(false);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
