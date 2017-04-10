package ua.com.foxminded.serviceacc.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.com.foxminded.serviceacc.config.PersistenceConfig;
import ua.com.foxminded.serviceacc.model.ClientLevelType;
import ua.com.foxminded.serviceacc.model.ClientStatusType;

import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static ua.com.foxminded.serviceacc.repository.ModelBuilder.*;

/**
 * Created by andreb on 06.04.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
public class ClientLevelTypeTest {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ClientLevelTypeRepository clientLevelTypeRepository;

    @Before
    public void fillClientStatusType(){
        List<ClientLevelType> statuses = ModelBuilder.buildListTestClientLevelType();
        clientLevelTypeRepository.save(statuses);
        assertThat(clientLevelTypeRepository.findAll(), hasSize(3));
    }

    @After
    public void deleteData(){
        clientRepository.deleteAll();
        clientLevelTypeRepository.deleteAll();
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void saveTheSameStatusTest() {
        ClientLevelType doubleBeginner = new ClientLevelType("01", "Beginner");
        clientLevelTypeRepository.save(doubleBeginner); //Must throws error duplicate key value violates unique constraint

        assertThat(clientLevelTypeRepository.findAll(), hasSize(3));
    }

    @Test
    public void getLevelByStringTest(){
        ClientLevelType level = clientLevelTypeRepository.findOneByTitle(BEGINNER);
        assertThat(level, notNullValue());
        assertThat(level.getTitle(), is(BEGINNER));
        level = clientLevelTypeRepository.findOneByTitle(REGULAR);
        assertThat(level, notNullValue());
        assertThat(level.getTitle(), not(BEGINNER));
        assertThat(level.getTitle(), is(REGULAR));
    }
}
