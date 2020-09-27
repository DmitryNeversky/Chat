package com.chat.my.repositories;

import com.chat.my.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(long id);
    User findByName(String name);
}
