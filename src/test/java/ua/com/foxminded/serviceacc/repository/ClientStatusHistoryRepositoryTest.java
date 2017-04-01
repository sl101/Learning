package ua.com.foxminded.serviceacc.repository;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.com.foxminded.serviceacc.config.PersistenceConfig;
import ua.com.foxminded.serviceacc.model.Client;
import ua.com.foxminded.serviceacc.model.ClientStatusHistory;
import ua.com.foxminded.serviceacc.model.constant.ClientStatus;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

/**
 * Created by andreb on 31.03.17.
 * Integration Test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
public class ClientStatusHistoryRepositoryTest {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ClientStatusHistoryRepository clientStatusHistoryRepository;

    @After
    public void deleteData(){
        clientStatusHistoryRepository.deleteAll();
        clientRepository.deleteAll();
    }

    @Test
    public void saveClientStatusHistory(){
        Client client = ModelBuilder.buildTestClient();
        clientRepository.save(client);
        ClientStatusHistory csh = ModelBuilder.buildTestClientHistory(client, client.getStatus());
        clientStatusHistoryRepository.save(csh);
        assertThat(clientRepository.findAll(), hasSize(1));
        assertThat(clientStatusHistoryRepository.findAll(), hasSize(1));
        client.setStatus(ClientStatus.Frozen);
        csh = ModelBuilder.buildTestClientHistory(client, client.getStatus());
        clientStatusHistoryRepository.save(csh);
        assertThat(clientRepository.findAll(), hasSize(1));
        assertThat(clientStatusHistoryRepository.findAll(), hasSize(2));

    }
}
