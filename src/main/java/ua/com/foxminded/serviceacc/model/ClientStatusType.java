package ua.com.foxminded.serviceacc.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by andreb on 04.04.17.
 * Represent Client status
 */
@Entity
@Table(name = "client_status_type")
@Getter @Setter
@NoArgsConstructor
public class ClientStatusType {

    @Id
    @SequenceGenerator(name = "generator", sequenceName = "client_status_type_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column (name = "code", unique = true, nullable = false)
    private String code;

    @Column (name = "title", nullable = false)
    private String title;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "status", orphanRemoval = true)
    private Set<Client> clients = new HashSet<>();
    
    @Column(name = "active", nullable = false)
	private boolean active = true;

    public ClientStatusType(String code, String title) {
        this.code = code;
        this.title = title;
    }	

}
