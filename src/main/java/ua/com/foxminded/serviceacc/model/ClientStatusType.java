package ua.com.foxminded.serviceacc.model;

import javax.persistence.*;

/**
 * Created by andreb on 04.04.17.
 * Represent Client status
 */
@Entity
@Table(name = "client_status_type")
public class ClientStatusType {
    @Id
    @SequenceGenerator(name = "generator", sequenceName = "client_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column (name = "status", unique = true, nullable = false)
    private String status;

    public ClientStatusType() {
    }

    public ClientStatusType(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
