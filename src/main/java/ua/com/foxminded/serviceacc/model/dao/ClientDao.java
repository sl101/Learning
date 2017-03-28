package ua.com.foxminded.serviceacc.model.dao;

import java.util.List;

import ua.com.foxminded.serviceacc.model.domain.Client;

public interface ClientDao {

	public List<Client> getAll();

	public void save(Client client);

	public void delete(Client client);
}
