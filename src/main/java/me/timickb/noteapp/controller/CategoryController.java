package me.timickb.noteapp.controller;

import me.timickb.noteapp.exception.EntityNotFoundException;
import me.timickb.noteapp.model.request.CategoryCreateRequest;
import me.timickb.noteapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(categoryService.getAll());
        } catch(Exception e) {
            return ResponseEntity.ok("An error occurred");
        }
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity getOne(@PathVariable Long categoryId) {
        try {
            return ResponseEntity.ok(categoryService.getOne(categoryId));
        } catch(EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CategoryCreateRequest request) {
        try {
            return ResponseEntity.ok(categoryService.create(request));
        } catch(EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
