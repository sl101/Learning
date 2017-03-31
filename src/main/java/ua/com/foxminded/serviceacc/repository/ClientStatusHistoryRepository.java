package ua.com.foxminded.serviceacc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ua.com.foxminded.serviceacc.model.Client;
import ua.com.foxminded.serviceacc.model.ClientStatusHistory;

import java.util.List;

/**
 * Created by andreb on 31.03.17.
 */
public interface ClientStatusHistoryRepository extends JpaRepository<ClientStatusHistory, Long>, JpaSpecificationExecutor{
    List<ClientStatusHistory> findByClient(Client client);
}
