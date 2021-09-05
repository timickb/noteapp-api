package me.timickb.noteapp.model.response;

import lombok.Data;
import me.timickb.noteapp.model.User;

import java.util.List;

@Data
public class UserResponse {
    private long id;
    private String login;
    private String email;
    private List<CategoryResponse> categories;

}
