package ua.com.foxminded.serviceacc.service;


import ua.com.foxminded.serviceacc.model.Manager;

import java.util.List;

/**
 * Created by andreb on 31.03.17.
 */
public interface ManagerService {

    Manager create(Manager manager);
    Manager update(Manager manager);
    Manager findById(Long managerId);
    List<Manager> findAll();
    void delete(Long managerId);
}
