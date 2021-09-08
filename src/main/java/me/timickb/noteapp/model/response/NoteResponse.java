package me.timickb.noteapp.model.response;

import lombok.Data;

@Data
public class NoteResponse {
    public long id;
    public String text;
    public String title;
}
