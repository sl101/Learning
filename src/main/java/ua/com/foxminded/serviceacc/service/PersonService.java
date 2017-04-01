package ua.com.foxminded.serviceacc.service;


import ua.com.foxminded.serviceacc.model.Person;

import java.util.List;

/**
 * Created by andreb on 31.03.17.
 */
public interface PersonService {

    Person create(Person person);
    Person update(Person person);
    Person findById(Long personId);
    List<Person> findAll();
    void delete(Long personId);
}
