package ua.com.foxminded.serviceacc.repository;

import ua.com.foxminded.serviceacc.model.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by andreb on 30.03.17.
 */
public class ModelBuilder {

    public static final String BEGINNER = "Beginner";
    public static final String APPLICANT = "Applicant";
    public static final String REGULAR = "Regular";

    public static final String ACTIVE = "Active";
    public static final String FROZEN = "Frozen";
    public static final String PENDING = "Pending";

    public static Contact buildTestContact(){
        Contact contact = new Contact();
        contact.setContactName("066-123-45-67");
        contact.setContactType(null);
        contact.setPerson(null);

        return contact;
    }

    public static Person buildTestPerson(){
        Person person = new Person();
        person.setFirstName("Ivan");
        person.setLastName("Petrov");
        person.setBirthday(new Date());

        Contact contact = buildTestContact();
        contact.setPerson(person);

        return person;
    }

    public static Client buildTestClient(){
        Person person = buildTestPerson();
        Client client = new Client();
        client.setPerson(person);
        client.setStatus(null);
        client.setLevel(null);
        client.setManager(null);

        return client;
    }

    public static Manager buildTestManager(){
        Person person = buildTestPerson();
        person.setFirstName("Andrey");
        person.setLastName("Sidorov");

        Client client = buildTestClient();

        Manager manager = new Manager();
        manager.setPerson(person);
        manager.getClients().add(client);

        return manager;
    }

    public static ClientStatusHistory buildTestClientHistory(Client client, ClientStatusType changedStatus){
        ClientStatusHistory clientStatusHistory = new ClientStatusHistory(client, changedStatus, new Date());

        return clientStatusHistory;
    }

    public static List<ClientStatusType> buildListTestClientStatusType(){
        List<ClientStatusType> statuses = new ArrayList<>();
        ClientStatusType active = new ClientStatusType("01", ACTIVE);
        ClientStatusType frozen = new ClientStatusType("02", FROZEN);
        ClientStatusType pending = new ClientStatusType("00", PENDING);
        statuses.add(active);
        statuses.add(frozen);
        statuses.add(pending);
        return statuses;
    }

    public static List<ClientLevelType> buildListTestClientLevelType(){
        List<ClientLevelType> levels = new ArrayList<>();
        levels.add(new ClientLevelType("01", BEGINNER));
        levels.add(new ClientLevelType("00", APPLICANT));
        levels.add(new ClientLevelType("02", REGULAR));

        return levels;
    }


}
