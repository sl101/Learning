package ua.com.foxminded.serviceacc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.serviceacc.model.ContactType;
import ua.com.foxminded.serviceacc.repository.ContactTypeRepository;

import java.util.List;

/**
 * Created by andreb on 04.04.17.
 */
@Service("contactTypeService")
public class ContactTypeServiceDataJpa implements ContactTypeService{

    @Autowired
    ContactTypeRepository contactTypeRepository;

    @Override
    public ContactType save(ContactType contactType) {
        return contactTypeRepository.save(contactType);
    }

    @Override
    public ContactType update(ContactType contactType) {
        return contactTypeRepository.save(contactType);
    }

    @Override
    public ContactType findById(Long contactTypeId) {
        return contactTypeRepository.findOne(contactTypeId);
    }

    @Override
    public ContactType findByTypeName(String contactTypeName) {
        return contactTypeRepository.findOneByTitle(contactTypeName);
    }

    @Override
    public List<ContactType> findAll() {
        return contactTypeRepository.findAll();
    }

    @Override
    public void delete(Long contactTypeId) {
        contactTypeRepository.delete(contactTypeId);
    }
}
