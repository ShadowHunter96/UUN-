package com.example.fullstack_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by User: Vu
 * Date: 15.06.2024
 * Time: 13:11
 */
@Entity
@Getter
@Setter
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "techjob_id")
    private Long techJobId;
    @Column(name = "applicant_id")
    private Long applicantId;
}

