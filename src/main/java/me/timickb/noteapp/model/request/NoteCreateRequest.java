package me.timickb.noteapp.model.request;

import lombok.Data;

@Data
public class NoteCreateRequest {
    private String title;
    private String text;
    private Long categoryId;
}
