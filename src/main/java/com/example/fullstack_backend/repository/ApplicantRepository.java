package com.example.fullstack_backend.repository;

import com.example.fullstack_backend.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Created by User: Vu
 * Date: 29.02.2024
 * Time: 12:34
 */
public interface ApplicantRepository extends JpaRepository<Applicant,Long> {
    @Query("SELECT a.id FROM Applicant a WHERE a.name = :name AND a.surname = :surname")
    Optional<Long> findApplicantIdByNameAndSurname(@Param("name") String name, @Param("surname") String surname);

    Optional<Applicant> findApplicantByEmail(String email);





}
