package qq.upyachka.js.challenge.core.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Represents responsibility sign of {@link User}.
 * Created on 24.02.17.
 * @author upyachka.
 */
@Entity
@Table(name = "role")
public class Role {

    /** Unique identifier. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** Role name. */
    private String name;

    /** Users with this role assigned. */
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    /** @returns value of {@link #id}. */
    public Long getId() {
        return id;
    }

    /** @param {@link #id} value for {@link #id}. */
    public void setId(Long id) {
        this.id = id;
    }

    /** @returns value of {@link #name}. */
    public String getName() {
        return name;
    }

    /** @param {@link #name} value for {@link #name}. */
    public void setName(String name) {
        this.name = name;
    }
}