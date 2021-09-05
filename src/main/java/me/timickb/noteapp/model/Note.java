package me.timickb.noteapp.model;

import lombok.Data;
import me.timickb.noteapp.model.request.NoteCreateRequest;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Note extends EntityBase {
    public String title;
    public String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    public Category category;

    public static Note createFromRequest(NoteCreateRequest request, Category category) {
        Note note = new Note();
        note.setCategory(category);
        note.setUser(category.getUser());
        note.setText(request.getText());
        note.setTitle(request.getTitle());
        return note;
    }
}
