package ua.com.foxminded.serviceacc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ua.com.foxminded.serviceacc.model.ClientLevelType;

/**
 * Created by andreb on 04.04.17.
 */
public interface ClientLevelTypeRepository extends JpaRepository<ClientLevelType, Long>, JpaSpecificationExecutor{
    ClientLevelType findOneByLevel(String levelName);
}
