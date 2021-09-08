package me.timickb.noteapp.mapper;

import me.timickb.noteapp.model.Category;
import me.timickb.noteapp.model.response.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface CategoryMapper {
    @Mappings({
            @Mapping(target = "id", source = "category.id"),
            @Mapping(target = "title", source = "category.title")
    })
    CategoryResponse categoryToResponse(Category category);
}
