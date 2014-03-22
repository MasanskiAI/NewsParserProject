package Models;

import javax.persistence.*;

/**
 * @author Taldykin V.S.
 * @version 1.00 14.03.14 0:24
 */
@Entity
@Table(name = "PROFILES")
public class Profile {

    private Long id;
    private String name;
    private String text;

    public Profile() {
    }

    public Profile(Long id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "TEXT")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
