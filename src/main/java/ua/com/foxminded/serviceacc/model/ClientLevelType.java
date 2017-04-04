package ua.com.foxminded.serviceacc.model;

import javax.persistence.*;

/**
 * Created by andreb on 04.04.17.
 * Class represent client graduate level
 */
@Entity
@Table(name = "client_level_type")
public class ClientLevelType {
    @Id
    @SequenceGenerator(name = "generator", sequenceName = "client_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @Column (name = "id", unique = true, nullable = false)
    private Long id;
    @Column (name = "level", unique = true, nullable = false)
    private String level;

    public ClientLevelType(){
    }
    public ClientLevelType(String level){
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
