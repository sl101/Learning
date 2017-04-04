package ua.com.foxminded.serviceacc.service;

import ua.com.foxminded.serviceacc.model.ClientLevelType;

import java.util.List;

/**
 * Created by andreb on 04.04.17.
 */
public interface ClientLevelTypeService {

    ClientLevelType save(ClientLevelType clientLevelType);
    ClientLevelType update(ClientLevelType clientLevelType);
    ClientLevelType findById(Long clientLevelTypeId);
    ClientLevelType findByLevelName(String levelName);
    List<ClientLevelType> findAll();
    void delete(Long clientLevelTypeId);
}
