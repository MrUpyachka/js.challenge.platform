package qq.upyachka.js.contest.core.model.user;

import com.google.common.collect.Sets;
import qq.upyachka.js.contest.core.model.script.ScriptExecutionResultDo;

import javax.persistence.*;
import java.util.Set;

/**
 * Represent platform user.
 * Created on 24.02.17.
 * @author upyachka.
 */
@Entity
@Table(name = "user_", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "username"})})
public class UserDo {

    /** Unique identifier. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** UserDo-friendly identifier. */
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    /** UserDo password. */
    @Column(name = "password", nullable = false)
    private String password;

    /** Set of assigned to user roles. */
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = Sets.newHashSet();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<ScriptExecutionResultDo> executions = Sets.newHashSet();

    /** @return value of {@link #executions}. */
    public Set<ScriptExecutionResultDo> getExecutions() {
        return executions;
    }

    /** @param executions value for {@link #executions}. */
    public void setExecutions(Set<ScriptExecutionResultDo> executions) {
        this.executions = executions;
    }

    /** @return value of {@link #id}. */
    public Long getId() {
        return id;
    }

    /** @param id value for {@link #id}. */
    public void setId(Long id) {
        this.id = id;
    }

    /** @return value of {@link #username}. */
    public String getUsername() {
        return username;
    }

    /** @param username value for {@link #username}. */
    public void setUsername(String username) {
        this.username = username;
    }

    /** @return value of {@link #password}. */
    public String getPassword() {
        return password;
    }

    /** @param password value for {@link #password}. */
    public void setPassword(String password) {
        this.password = password;
    }

    /** @return value of {@link #roles}. */
    public Set<Role> getRoles() {
        return roles;
    }

    /** @param roles value for {@link #roles}. */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
