package ua.com.foxminded.serviceacc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ua.com.foxminded.serviceacc.model.Manager;

/**
 * Created by andreb on 31.03.17.
 */
public interface ManagerRepository extends JpaRepository<Manager, Long>, JpaSpecificationExecutor {
}
