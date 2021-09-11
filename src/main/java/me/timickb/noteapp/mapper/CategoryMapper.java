package me.timickb.noteapp.mapper;

import me.timickb.noteapp.model.Category;
import me.timickb.noteapp.model.response.CategoryResponse;
import me.timickb.noteapp.service.CategoryService;
import me.timickb.noteapp.service.NoteService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses= NoteService.class)
public abstract class CategoryMapper {

    @Autowired
    protected NoteService noteService;

    @Mappings({
            @Mapping(target = "notes", expression = "java(noteService.getAllByCategoryIdAsResponse(category.getId()))"),
            @Mapping(target = "id", source = "category.id"),
            @Mapping(target = "title", source = "category.title")
    })
    public abstract CategoryResponse categoryToResponse(Category category);
}
