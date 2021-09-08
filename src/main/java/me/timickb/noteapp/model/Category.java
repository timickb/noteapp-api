package me.timickb.noteapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import me.timickb.noteapp.model.request.CategoryCreateRequest;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class Category extends EntityBase {
    public String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    public Set<Note> notes;

    public static Category createFromRequest(CategoryCreateRequest request, User user) {
        Category category = new Category();
        category.setUser(user);
        category.setTitle(request.getTitle());
        category.setCreated(new Date());
        category.setUpdated(new Date());

        return category;
    }
}
