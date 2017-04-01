package ua.com.foxminded.serviceacc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.serviceacc.model.Person;
import ua.com.foxminded.serviceacc.repository.PersonRepository;

import java.util.List;

/**
 * Created by andreb on 31.03.17.
 */
@Service("personServiceRepository")
public class PersonServiceRepository implements PersonService{

    @Autowired
    PersonRepository personRepository;

    @Override
    public Person create(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person update(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person findById(Long personId) {
        return personRepository.findOne(personId);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public void delete(Long personId) {
        personRepository.delete(personId);
    }
}
