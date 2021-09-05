package me.timickb.noteapp.service;

import me.timickb.noteapp.exception.EntityNotFoundException;
import me.timickb.noteapp.model.Category;
import me.timickb.noteapp.model.User;
import me.timickb.noteapp.model.request.CategoryCreateRequest;
import me.timickb.noteapp.repository.CategoryRepository;
import me.timickb.noteapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Iterable<Category> getAllByUserId(Long userId) throws EntityNotFoundException {
        User user = userRepo.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User with such id doesn't exist"));
        return categoryRepo.findAllByUser(user);
    }

    public Category create(CategoryCreateRequest request) throws EntityNotFoundException {
        User user = userRepo.findById(request.getUserId()).orElseThrow(
                () -> new EntityNotFoundException("User with such id doesn't exist"));
        Category category = Category.createFromRequest(request, user);
        categoryRepo.save(category);
        return category;
    }
}

