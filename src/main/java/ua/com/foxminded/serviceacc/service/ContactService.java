package ua.com.foxminded.serviceacc.service;

import ua.com.foxminded.serviceacc.model.Contact;

import java.util.List;

/**
 * Created by andreb on 31.03.17.
 */
public interface ContactService {

    Contact create(Contact contact);
    Contact update(Contact contact);
    Contact findById(Long contactId);
    List<Contact> findAll();
    void delete(Long contactId);
}
