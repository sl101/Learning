package ua.com.foxminded.serviceacc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.serviceacc.model.Client;
import ua.com.foxminded.serviceacc.model.ClientStatusType;

import java.util.List;

/**
 * Created by andreb on 04.04.17.
 */
public interface ClientStatusTypeRepository extends JpaRepository<ClientStatusType, Long>, JpaSpecificationExecutor {
    ClientStatusType findOneByStatus(String status);
    @Query("SELECT c FROM ClientStatusType c JOIN FETCH c.clients WHERE c.status = ?1")
    ClientStatusType findAllAndFetchClientEagerly(String status);
}
