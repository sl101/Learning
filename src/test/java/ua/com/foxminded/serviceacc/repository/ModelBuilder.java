package ua.com.foxminded.serviceacc.repository;

import ua.com.foxminded.serviceacc.model.Client;
import ua.com.foxminded.serviceacc.model.Contact;
import ua.com.foxminded.serviceacc.model.Person;
import ua.com.foxminded.serviceacc.model.constant.ClientLevel;
import ua.com.foxminded.serviceacc.model.constant.ClientStatus;
import ua.com.foxminded.serviceacc.model.constant.ContactType;

import java.util.Date;

/**
 * Created by andreb on 30.03.17.
 */
public class ModelBuilder {

    public static Contact buildTestContact(){
        Contact contact = new Contact();
        contact.setContactDefinition("066-123-45-67");
        contact.setContactType(ContactType.phone);
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
        client.setStatus(ClientStatus.Active);
        client.setLevel(ClientLevel.Applicant);
        client.setManager(null);

        return client;
    }


}
