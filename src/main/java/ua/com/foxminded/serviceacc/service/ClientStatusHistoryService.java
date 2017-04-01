package ua.com.foxminded.serviceacc.service;


import ua.com.foxminded.serviceacc.model.Client;
import ua.com.foxminded.serviceacc.model.ClientStatusHistory;

import java.util.List;

/**
 * Created by andreb on 31.03.17.
 */
public interface ClientStatusHistoryService {

    ClientStatusHistory save(ClientStatusHistory clientStatusHistory);
    ClientStatusHistory update(ClientStatusHistory clientStatusHistory);
    ClientStatusHistory findById(Long clientStatusHistoryId);
    List<ClientStatusHistory> findByClient(Client client);
    List<ClientStatusHistory> findAll();
    void delete(Long clientStatusHistoryId);
}
