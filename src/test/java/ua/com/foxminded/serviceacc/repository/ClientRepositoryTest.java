package ua.com.foxminded.serviceacc.repository;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.com.foxminded.serviceacc.config.PersistenceConfig;
import ua.com.foxminded.serviceacc.model.Client;
import ua.com.foxminded.serviceacc.model.ClientLevelType;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by andreb on 30.03.17.
 * Integration Test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
public class ClientRepositoryTest {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ContactRepository contactRepository;

    @After
    public void deleteData(){
        clientRepository.deleteAll();
        contactRepository.deleteAll();
    }

    @Test
    public void saveClient(){
        Client client = ModelBuilder.buildTestClient();
        clientRepository.save(client);
        assertThat(clientRepository.findAll(), hasSize(1));
    }

    @Test
    public void updateClient(){
        Client client = ModelBuilder.buildTestClient();
        clientRepository.save(client);
        assertThat(clientRepository.findAll(), hasSize(1));
    }


    @Test
    public void deleteClient(){
        Client client = ModelBuilder.buildTestClient();
        clientRepository.save(client);
        assertThat(clientRepository.findAll(), hasSize(1));
        clientRepository.delete(client);
        assertThat(clientRepository.findAll(), hasSize(0));
    }


}
