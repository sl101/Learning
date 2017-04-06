package ua.com.foxminded.serviceacc.service;

import ua.com.foxminded.serviceacc.model.ContactType;

import java.util.List;

/**
 * Created by andreb on 04.04.17.
 */
public interface ContactTypeService {

    ContactType save(ContactType contactType);
    ContactType update(ContactType contactType);
    ContactType findById(Long contactTypeId);
    ContactType findByTypeName(String contactTypeName);
    List<ContactType> findAll();
    void delete(Long contactTypeId);
}
