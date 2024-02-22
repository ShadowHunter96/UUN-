package com.example.fullstack_backend.repository;

import com.example.fullstack_backend.model.TechJob;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by User: Vu
 * Date: 22.02.2024
 * Time: 20:18
 */

public interface TechJobRepository extends JpaRepository<TechJob,Long> {
}
