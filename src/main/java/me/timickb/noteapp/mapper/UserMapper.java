package me.timickb.noteapp.mapper;

import me.timickb.noteapp.model.User;
import me.timickb.noteapp.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse toResponse(User user);
}
