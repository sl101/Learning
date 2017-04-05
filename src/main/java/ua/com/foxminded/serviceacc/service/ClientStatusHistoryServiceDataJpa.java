package ua.com.foxminded.serviceacc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.serviceacc.model.Client;
import ua.com.foxminded.serviceacc.model.ClientStatusHistory;
import ua.com.foxminded.serviceacc.repository.ClientStatusHistoryRepository;

import java.util.List;

/**
 * Created by andreb on 31.03.17.
 */
@Service("clientStatusHistoryService")
public class ClientStatusHistoryServiceDataJpa implements ClientStatusHistoryService {

    @Autowired
    ClientStatusHistoryRepository clientStatusHistoryRepository;

    @Override
    public ClientStatusHistory save(ClientStatusHistory clientStatusHistory) {
        return clientStatusHistoryRepository.save(clientStatusHistory);
    }

    @Override
    public ClientStatusHistory update(ClientStatusHistory clientStatusHistory) {
        return clientStatusHistoryRepository.save(clientStatusHistory);
    }

    @Override
    public ClientStatusHistory findById(Long clientStatusHistoryId) {
        return clientStatusHistoryRepository.findOne(clientStatusHistoryId);
    }

    @Override
    public List<ClientStatusHistory> findByClient(Client client) {
        return clientStatusHistoryRepository.findByClient(client);
    }

    @Override
    public List<ClientStatusHistory> findAll() {
        return clientStatusHistoryRepository.findAll();
    }

    @Override
    public void delete(Long clientStatusHistoryId) {
        clientStatusHistoryRepository.delete(clientStatusHistoryId);
    }
}
