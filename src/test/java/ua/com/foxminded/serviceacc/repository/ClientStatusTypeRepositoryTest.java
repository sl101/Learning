package ua.com.foxminded.serviceacc.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.com.foxminded.serviceacc.config.PersistenceConfig;
import ua.com.foxminded.serviceacc.model.Client;
import ua.com.foxminded.serviceacc.model.ClientStatusType;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static ua.com.foxminded.serviceacc.repository.ModelBuilder.*;
/**
 * Created by andreb on 05.04.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
public class ClientStatusTypeRepositoryTest {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ClientStatusTypeRepository clientStatusTypeRepository;
    
    @Before
    public void fillClientStatusType(){
        List<ClientStatusType> statuses = ModelBuilder.buildListTestClientStatusType();
        clientStatusTypeRepository.save(statuses);
        assertThat(clientStatusTypeRepository.findAll(), hasSize(3));
    }

    @After
    public void deleteData(){
        clientRepository.deleteAll();
        clientStatusTypeRepository.deleteAll();
    }

    @Test
    public void getStatusByStringTest(){
        ClientStatusType status = clientStatusTypeRepository.findOneByTitle(ACTIVE);
        assertThat(status.getTitle(), notNullValue());
        assertThat(status.getTitle(), is(ACTIVE));
        status = clientStatusTypeRepository.findOneByTitle(FROZEN);
        assertThat(status.getTitle(), notNullValue());
        assertThat(status.getTitle(), is(FROZEN));
        status = clientStatusTypeRepository.findOneByTitle(FROZEN);
        assertThat(status.getTitle(), notNullValue());
        assertThat(status.getTitle(), not(PENDING));
        status = clientStatusTypeRepository.findOneByTitle(PENDING);
        assertThat(status.getTitle(), notNullValue());
        assertThat(status.getTitle(), is(PENDING));
    }

    @Test
    public void updateStatusTest(){
        String other = "Other";
        ClientStatusType status = clientStatusTypeRepository.findOneByTitle(ACTIVE);
        assertThat(status.getTitle(), notNullValue());
        assertThat(status.getTitle(), is(ACTIVE));
        status.setTitle(other);
        clientStatusTypeRepository.save(status);
        assertThat(clientStatusTypeRepository.findAll(), hasSize(3));

        ClientStatusType fetchedStatus = clientStatusTypeRepository.findOneByTitle(other);
        assertThat(status.getTitle(), notNullValue());
        assertThat(status.getTitle(), is(other));
    }

    @Test
    public void getClientByStatusTest(){
        ClientStatusType active = clientStatusTypeRepository.findOneByTitle(ACTIVE);

        Client client1 = ModelBuilder.buildTestClient();
        client1.setStatus(active);

        Client client2 = ModelBuilder.buildTestClient();
        client2.setStatus(active);

        Client client3 = ModelBuilder.buildTestClient();
        client3.setStatus(active);

        clientRepository.save(client1);
        clientRepository.save(client2);
        clientRepository.save(client3);

        assertThat(clientStatusTypeRepository.findAllAndFetchClientEagerly(ACTIVE).getClients().size(), is(3));

    }

}
