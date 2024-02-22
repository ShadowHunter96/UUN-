package com.example.fullstack_backend.repository;

import com.example.fullstack_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by User: Vu
 * Date: 19.12.2023
 * Time: 18:53
 */

public interface UserRepository extends JpaRepository<User,Long> {
}
