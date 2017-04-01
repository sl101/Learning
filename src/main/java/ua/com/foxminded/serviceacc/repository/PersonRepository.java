package ua.com.foxminded.serviceacc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ua.com.foxminded.serviceacc.model.Person;

/**
 * Created by andreb on 30.03.17.
 */
public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor {
}
