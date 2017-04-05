package ua.com.foxminded.serviceacc.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.awt.PaintEventDispatcher;
import ua.com.foxminded.serviceacc.config.PersistenceConfig;
import ua.com.foxminded.serviceacc.model.Client;
import ua.com.foxminded.serviceacc.model.ClientStatusType;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

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
    @Autowired
    PersonRepository personRepository;

    private static final String ACTIVE = "Active";
    private static final String FROZEN = "Frozen";
    private static final String PENDING = "Pending";

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
        ClientStatusType status = clientStatusTypeRepository.findOneByStatus(ACTIVE);
        assertThat(status.getStatus(), notNullValue());
        assertThat(status.getStatus(), is(ACTIVE));
        status = clientStatusTypeRepository.findOneByStatus(FROZEN);
        assertThat(status.getStatus(), notNullValue());
        assertThat(status.getStatus(), is(FROZEN));
        status = clientStatusTypeRepository.findOneByStatus(FROZEN);
        assertThat(status.getStatus(), notNullValue());
        assertThat(status.getStatus(), not(PENDING));
        status = clientStatusTypeRepository.findOneByStatus(PENDING);
        assertThat(status.getStatus(), notNullValue());
        assertThat(status.getStatus(), is(PENDING));
    }

    @Test
    public void updateStatusTest(){
        String other = "Other";
        ClientStatusType status = clientStatusTypeRepository.findOneByStatus(ACTIVE);
        assertThat(status.getStatus(), notNullValue());
        assertThat(status.getStatus(), is(ACTIVE));
        status.setStatus(other);
        clientStatusTypeRepository.save(status);
        assertThat(clientStatusTypeRepository.findAll(), hasSize(3));

        ClientStatusType fetchedStatus = clientStatusTypeRepository.findOneByStatus(other);
        assertThat(status.getStatus(), notNullValue());
        assertThat(status.getStatus(), is(other));
    }

    @Test
    public void getClientByStatusTest(){
        ClientStatusType active = clientStatusTypeRepository.findOneByStatus(ACTIVE);

        Client client1 = ModelBuilder.buildTestClient();
        client1.setStatus(active);

        Client client2 = ModelBuilder.buildTestClient();
        client2.setStatus(active);

        Client client3 = ModelBuilder.buildTestClient();
        client3.setStatus(active);

        personRepository.save(client1.getPerson());
        clientRepository.save(client1);
        personRepository.save(client2.getPerson());
        clientRepository.save(client2);
        personRepository.save(client3.getPerson());
        clientRepository.save(client3);

        assertThat(clientStatusTypeRepository.findAllAndFetchClientEagerly(ACTIVE).getClients().size(), is(3));

    }

}
