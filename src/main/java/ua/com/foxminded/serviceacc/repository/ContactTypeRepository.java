package ua.com.foxminded.serviceacc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ua.com.foxminded.serviceacc.model.ContactType;

import java.util.List;

/**
 * Created by andreb on 04.04.17.
 */
public interface ContactTypeRepository extends JpaRepository<ContactType, Long>, JpaSpecificationExecutor {
    ContactType findOneByTitle(String contactTypeName);
}
