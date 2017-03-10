package qq.upyachka.js.contest.core.model.user;

import javax.persistence.*;
import java.util.Set;

/**
 * Represents responsibility sign of {@link UserDo}.
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
    private Set<UserDo> userDos;

    /** @return value of {@link #userDos}. */
    public Set<UserDo> getUsers() {
        return userDos;
    }

    /** @param userDos value for {@link #userDos}. */
    public void setUsers(Set<UserDo> userDos) {
        this.userDos = userDos;
    }

    /** @return value of {@link #id}. */
    public Long getId() {
        return id;
    }

    /** @param id value for {@link #id}. */
    public void setId(Long id) {
        this.id = id;
    }

    /** @return value of {@link #name}. */
    public String getName() {
        return name;
    }

    /** @param name value for {@link #name}. */
    public void setName(String name) {
        this.name = name;
    }
}
