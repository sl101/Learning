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
        contact.setClient(null);
        contact.setManager(null);
        
        return contact;
    }

    public static Client buildTestClient(){
        Client client = new Client();
        client.setFirstName("Andrey");
        client.setLastName("Sidorov");
        client.setStatus(null);
        client.setLevel(null);
        client.setManager(null);

        return client;
    }

    public static Manager buildTestManager(){
        Client client = buildTestClient();
        Manager manager = new Manager();
        manager.setFirstName("Andrey");
        manager.setLastName("Sidorov");
        manager.getClients().add(client);

        return manager;
    }

    public static ClientStatusHistory buildTestClientHistory(Client client, ClientStatusType changedStatus){
        ClientStatusHistory clientStatusHistory = new ClientStatusHistory(client, changedStatus, new Date());

        return clientStatusHistory;
    }

    public static List<ClientStatusType> buildListTestClientStatusType(){
        List<ClientStatusType> statuses = new ArrayList<>();
        ClientStatusType active = new ClientStatusType("1", ACTIVE);
        ClientStatusType frozen = new ClientStatusType("2", FROZEN);
        ClientStatusType pending = new ClientStatusType("3", PENDING);
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
