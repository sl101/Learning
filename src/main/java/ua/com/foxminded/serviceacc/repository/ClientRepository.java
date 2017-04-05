package ua.com.foxminded.serviceacc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.serviceacc.model.Client;

import java.util.List;

/**
 * Created by andreb on 30.03.17.
 */
public interface ClientRepository extends JpaRepository<Client, Long>, JpaSpecificationExecutor {
    /*
    * This method return List of Clients with eagerly fetched Person to avoid LazyInitiationException in UI layer
    */
    @Query("SELECT c FROM Client c JOIN FETCH c.person")
    List<Client> findAllAndFetchPersonEager();

    /*
    * This method return List of Clients with eagerly fetched ClientStatusHistory collection to avoid LazyInitiationException in UI layer
    */
    @Query("SELECT c FROM Client c JOIN FETCH c.clientHistory")
    List<Client> findAllAndFetchClientStatusHistoryEager();
}