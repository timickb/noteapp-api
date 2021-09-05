package me.timickb.noteapp.repository;

import me.timickb.noteapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByLoginAndPassword(String login, String password);
}



