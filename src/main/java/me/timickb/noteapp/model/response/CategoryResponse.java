package me.timickb.noteapp.model.response;

import lombok.Data;
import org.aspectj.weaver.ast.Not;

import java.util.List;

@Data
public class CategoryResponse {
    private long id;
    private String title;
    private List<NoteResponse> notes;
}
