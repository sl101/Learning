package ua.com.foxminded.serviceacc.service;

import ua.com.foxminded.serviceacc.model.ClientStatusType;

import java.util.List;

/**
 * Created by andreb on 04.04.17.
 */
public interface ClientStatusTypeService {

    ClientStatusType save(ClientStatusType clientStatusType);
    ClientStatusType update(ClientStatusType clientStatusType);
    ClientStatusType findById(Long clientStatusTypeId);
    ClientStatusType findByStatusName(String statusName);
    List<ClientStatusType> findAll();
    void delete(Long clientStatusTypeId);
}
