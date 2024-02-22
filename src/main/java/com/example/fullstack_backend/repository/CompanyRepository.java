package com.example.fullstack_backend.repository;

import com.example.fullstack_backend.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by User: Vu
 * Date: 22.02.2024
 * Time: 20:48
 */
public interface CompanyRepository extends JpaRepository<Company,Long> {
}
