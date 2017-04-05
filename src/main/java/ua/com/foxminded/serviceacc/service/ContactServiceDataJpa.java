package ua.com.foxminded.serviceacc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.serviceacc.model.Contact;
import ua.com.foxminded.serviceacc.repository.ContactRepository;

import java.util.List;

/**
 * Created by andreb on 31.03.17.
 */
@Service("contactService")
public class ContactServiceDataJpa implements ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Override
    public Contact create(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact findById(Long contactId) {
        return contactRepository.findOne(contactId);
    }

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public void delete(Long contactId) {
        contactRepository.delete(contactId);
    }
}
