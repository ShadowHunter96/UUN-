package com.example.fullstack_backend.repository;

import com.example.fullstack_backend.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by User: Vu
 * Date: 29.02.2024
 * Time: 12:34
 */
public interface ApplicantRepository extends JpaRepository<Applicant,Long> {
}
