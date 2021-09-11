package me.timickb.noteapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import me.timickb.noteapp.model.request.RegisterRequest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class User extends EntityBase {

    @Column(unique = true)
    public String login;

    @Column(unique = true)
    public String email;

    public String password;

    public static User createFromRequest(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setLogin(request.getLogin());
        user.setCreated(new Date());
        user.setUpdated(new Date());

        return user;
    }
}
