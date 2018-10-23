package internet.shop.entity;

import javax.persistence.*;
import java.util.Optional;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String login;
    private String password;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private UserStatus status;

    public User() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return String.format(
                "Camp[id=%d, name='%s', login='%s', password='%s']\r\n",
                id, name, login, password) ;
    }
}
