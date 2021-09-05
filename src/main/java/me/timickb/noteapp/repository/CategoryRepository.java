package me.timickb.noteapp.repository;

import me.timickb.noteapp.model.Category;
import me.timickb.noteapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByUser(User user);
}
