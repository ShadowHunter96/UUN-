package com.example.fullstack_backend.model;

import com.example.fullstack_backend.enums.Seniority;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;


import java.util.HashSet;
import java.util.Set;

/**
 * Created by User: Vu
 * Date: 29.02.2024
 * Time: 12:21
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "applicant")
public class Applicant {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String surname;
    @Column(unique = true)
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String selfDescription;
    private String linkedInUrl;
    @Enumerated(EnumType.STRING)
    private Seniority seniority;

    @ManyToMany(mappedBy = "applicants")
    private Set<TechJob> techJobs = new HashSet<>();
    @Column(name = "deleted",columnDefinition = "boolean default false")
    private boolean deleted;

}
