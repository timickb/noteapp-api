package me.timickb.noteapp.model.response;

import lombok.Data;
import me.timickb.noteapp.model.User;

import java.util.List;

@Data
public class UserResponse {
    public long id;
    public String login;
    public String email;
    public List<CategoryResponse> categories;

}
