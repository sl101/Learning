package ua.com.foxminded.serviceacc.repository;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.com.foxminded.serviceacc.config.PersistenceConfig;
import ua.com.foxminded.serviceacc.model.Contact;


import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by andreb on 30.03.17.
 * Integration Test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
public class ContactRepositoryTest {

    @Autowired
    ContactRepository contactRepository;

    @After
    public void deleteData(){
        contactRepository.deleteAll();
    }

    @Test
    public void saveContact(){
        Contact contact = ModelBuilder.buildTestContact();

        contactRepository.save(contact);
        assertThat(contactRepository.findAll(), hasSize(1));
    }

    @Test
    public void updateContact(){
        Contact contact = ModelBuilder.buildTestContact();
        contactRepository.save(contact);
        assertThat(contactRepository.findAll(), hasSize(1));
    }


    @Test
    public void deleteContact(){
        Contact contact = ModelBuilder.buildTestContact();
        contactRepository.save(contact);
        assertThat(contactRepository.findAll(), hasSize(1));

        contactRepository.delete(contact);
        assertThat(contactRepository.findOne(contact.getId()), nullValue());
    }
}
