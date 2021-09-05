package me.timickb.noteapp.model.response;

import lombok.Data;

@Data
public class NoteResponse {
    private long id;
    private String text;
    private String title;
}
