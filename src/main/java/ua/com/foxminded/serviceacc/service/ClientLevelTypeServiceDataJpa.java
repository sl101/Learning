package ua.com.foxminded.serviceacc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.serviceacc.model.ClientLevelType;
import ua.com.foxminded.serviceacc.repository.ClientLevelTypeRepository;

import java.util.List;

/**
 * Created by andreb on 04.04.17.
 */
@Service("clientLevelTypeService")
public class ClientLevelTypeServiceDataJpa implements ClientLevelTypeService {

    @Autowired
    ClientLevelTypeRepository clientLevelTypeRepository;

    @Override
    public ClientLevelType save(ClientLevelType clientLevelType) {
        return clientLevelTypeRepository.save(clientLevelType);
    }

    @Override
    public ClientLevelType update(ClientLevelType clientLevelType) {
        return clientLevelTypeRepository.save(clientLevelType);
    }

    @Override
    public ClientLevelType findById(Long clientLevelTypeId) {
        return clientLevelTypeRepository.findOne(clientLevelTypeId);
    }

    @Override
    public ClientLevelType findByLevelName(String levelName) {
        return clientLevelTypeRepository.findOneByTitle(levelName);
    }

    @Override
    public List<ClientLevelType> findAll() {
        return clientLevelTypeRepository.findAll();
    }

    @Override
    public void delete(Long clientLevelTypeId) {
        clientLevelTypeRepository.delete(clientLevelTypeId);
    }
}
