package ua.com.foxminded.serviceacc.repository;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.com.foxminded.serviceacc.config.PersistenceConfig;
import ua.com.foxminded.serviceacc.model.Contact;
import ua.com.foxminded.serviceacc.model.Person;

import javax.persistence.Persistence;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by andreb on 30.03.17.
 * Integration Test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
public class PersonRepositoryTest {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    ContactRepository contactRepository;

    @Before
    public void deleteData(){
        clientRepository.deleteAll();
        personRepository.deleteAll();
        contactRepository.deleteAll();
    }

    @Test
    public void savePerson() {
        Person person = ModelBuilder.buildTestPerson();

        personRepository.save(person);
        assertThat(personRepository.findAll(), hasSize(1));
    }

    @Test
    public void updatePerson() {
        Person person = ModelBuilder.buildTestPerson();

        personRepository.save(person);
        assertThat(personRepository.findAll(), hasSize(1));
        person.setLastName("Sidorov");
        personRepository.save(person);
        assertThat(personRepository.findOne(person.getId()).getLastName(), is("Sidorov"));
    }

    @Test
    public void deletePerson() {
        Person person = ModelBuilder.buildTestPerson();

        personRepository.save(person);
        assertThat(personRepository.findAll(), hasSize(1));

        personRepository.delete(person);
        assertThat(personRepository.findOne(person.getId()), nullValue());
    }
}
