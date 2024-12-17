package com.example.fullstack_backend.repository;

import com.example.fullstack_backend.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by User: Vu
 * Date: 15.06.2024
 * Time: 13:12
 */
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query("SELECT a FROM Answer a WHERE a.techJobId = :techJobId")
    List<Answer> findByTechJobId(@Param("techJobId") Long techJobId);
}

