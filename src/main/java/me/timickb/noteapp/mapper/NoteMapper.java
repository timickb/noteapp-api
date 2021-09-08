package me.timickb.noteapp.mapper;

import me.timickb.noteapp.model.Note;
import me.timickb.noteapp.model.response.NoteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import javax.persistence.SqlResultSetMappings;

@Mapper
public interface NoteMapper {

    @Mappings({
            @Mapping(target = "id", source = "note.id"),
            @Mapping(target = "title", source = "note.title"),
            @Mapping(target = "text", source = "note.text")
    })
    NoteResponse noteToResponse(Note note);

}
