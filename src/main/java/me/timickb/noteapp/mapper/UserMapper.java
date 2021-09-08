package me.timickb.noteapp.mapper;

import me.timickb.noteapp.model.User;
import me.timickb.noteapp.model.response.UserResponse;
import me.timickb.noteapp.service.CategoryService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses=CategoryService.class)
public abstract class UserMapper {

    @Autowired
    protected CategoryService categoryService;

    @Mappings({
            @Mapping(target="categories", expression = "java(categoryService.getAllByUserIdAsResponse(source.getId()))"),
            @Mapping(target="id", source="source.id"),
            @Mapping(target="login", source="source.login"),
            @Mapping(target="email", source="source.email")
    })
    public abstract UserResponse userToResponse(User source);
}
