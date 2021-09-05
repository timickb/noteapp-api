package me.timickb.noteapp.model.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String login;
    private String password;
    private String email;
}
