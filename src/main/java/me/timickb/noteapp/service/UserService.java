package me.timickb.noteapp.service;

import me.timickb.noteapp.exception.LoginException;
import me.timickb.noteapp.exception.EntityNotFoundException;
import me.timickb.noteapp.mapper.UserMapper;
import me.timickb.noteapp.model.User;
import me.timickb.noteapp.model.request.LoginRequest;
import me.timickb.noteapp.model.request.RegisterRequest;
import me.timickb.noteapp.model.response.UserResponse;
import me.timickb.noteapp.repository.CategoryRepository;
import me.timickb.noteapp.repository.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepo;
    private CategoryRepository categoryRepo;
    private UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepo, CategoryRepository categoryRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
        this.userMapper = userMapper;
    }

    public Iterable<UserResponse> getAll() {
        return userRepo.findAll().stream()
                .map(u -> userMapper.userToResponse(u))
                .collect(Collectors.toList());
    }

    public User getOne(Long id) throws EntityNotFoundException {
        return userRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User with such id doesn't exist"));
    }

    public User login(LoginRequest request) throws LoginException {
        Optional<User> user = userRepo.findFirstByLoginAndPassword(request.getLogin(), request.getPassword());
        return user.orElseThrow(LoginException::new);
    }

    public User register(RegisterRequest request) {
        User user = User.createFromRequest(request);
        userRepo.save(user);
        return user;
    }


}
