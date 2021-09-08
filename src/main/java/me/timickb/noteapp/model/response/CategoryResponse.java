package me.timickb.noteapp.model.response;

import lombok.Data;
import org.aspectj.weaver.ast.Not;

import java.util.List;

@Data
public class CategoryResponse {
    public long id;
    public String title;
    public List<NoteResponse> notes;
}
