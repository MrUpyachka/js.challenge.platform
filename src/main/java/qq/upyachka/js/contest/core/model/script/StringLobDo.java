package qq.upyachka.js.contest.core.model.script;

import javax.persistence.*;

/**
 * Large string entity. Script output, for example.
 * Created on 03.03.17.
 * @author upyachka.
 */
@Entity
@Table(name = "string_content")
public class StringLobDo {

    /** Unique identifier. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** Output of script execution. */
    @Lob
    private String content;

    /** Default constructor. */
    StringLobDo() {}

    /**
     * Constructor to build DO with content.
     * @param content an {@link String} content to be saved.
     */
    public StringLobDo(String content) {
        this.content = content;
    }

    /** @return value of {@link #content}. */
    public String getContent() {
        return content;
    }

    /** @param content value for {@link #content}. */
    public void setContent(String content) {
        this.content = content;
    }

    /** @return value of {@link #id}. */
    public Long getId() {
        return id;
    }

    /** @param id value for {@link #id}. */
    public void setId(Long id) {
        this.id = id;
    }

}
