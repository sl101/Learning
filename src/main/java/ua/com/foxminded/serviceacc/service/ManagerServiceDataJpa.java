package ua.com.foxminded.serviceacc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.serviceacc.model.Manager;
import ua.com.foxminded.serviceacc.repository.ManagerRepository;

import java.util.List;

/**
 * Created by andreb on 31.03.17.
 */
@Service("managerService")
public class ManagerServiceDataJpa implements ManagerService {

    @Autowired
    ManagerRepository managerRepository;

    @Override
    public Manager create(Manager manager) {
        return managerRepository.save(manager);
    }

    @Override
    public Manager update(Manager manager) {
        return managerRepository.save(manager);
    }

    @Override
    public Manager findById(Long managerId) {
        return managerRepository.findOne(managerId);
    }

    @Override
    public List<Manager> findAll() {
        return managerRepository.findAll();
    }

    @Override
    public void delete(Long managerId) {
        managerRepository.delete(managerId);
    }
}
