package me.timickb.noteapp.service;

import me.timickb.noteapp.exception.EntityNotFoundException;
import me.timickb.noteapp.mapper.CategoryMapper;
import me.timickb.noteapp.model.Category;
import me.timickb.noteapp.model.User;
import me.timickb.noteapp.model.request.CategoryCreateRequest;
import me.timickb.noteapp.model.response.CategoryResponse;
import me.timickb.noteapp.repository.CategoryRepository;
import me.timickb.noteapp.repository.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryService {
    private CategoryRepository categoryRepo;
    private UserRepository userRepo;

    @Autowired
    public CategoryService(CategoryRepository categoryRepo, UserRepository userRepo) {
        this.categoryRepo = categoryRepo;
        this.userRepo = userRepo;
    }

    public Iterable<Category> getAll() {
        return categoryRepo.findAll();
    }


    public Category getOne(Long id) throws EntityNotFoundException {
        return categoryRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Category with such id doesn't exist"));
    }

    public List<Category> getAllByUserId(Long userId) throws EntityNotFoundException {
        User user = userRepo.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User with such id doesn't exist"));
        return categoryRepo.findAllByUser(user);
    }

    public List<CategoryResponse> getAllByUserIdAsResponse(Long userId) {
        List<Category> categories = null;
        try {
            categories = getAllByUserId(userId);
        } catch (EntityNotFoundException e) {
            return new ArrayList<>();
        }
        return categories.stream()
                .map(c -> Mappers.getMapper(CategoryMapper.class).categoryToResponse(c))
                .collect(Collectors.toList());
    }

    public Category create(CategoryCreateRequest request) throws EntityNotFoundException {
        User user = userRepo.findById(request.getUserId()).orElseThrow(
                () -> new EntityNotFoundException("User with such id doesn't exist"));
        Category category = Category.createFromRequest(request, user);
        categoryRepo.save(category);
        return category;
    }
}

