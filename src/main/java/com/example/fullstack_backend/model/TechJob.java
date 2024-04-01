package com.example.fullstack_backend.model;


import com.example.fullstack_backend.enums.City;
import com.example.fullstack_backend.enums.Currency;
import com.example.fullstack_backend.enums.Education;
import com.example.fullstack_backend.enums.Seniority;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by User: Vu
 * Date: 22.02.2024
 * Time: 19:52
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "techjob")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class TechJob {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String baitText;
    private String description;
    @Enumerated(EnumType.STRING)
    private Seniority seniority;
    @Enumerated(EnumType.STRING)
    private Education education;
    @Enumerated(EnumType.STRING)
    private City city;
    private BigDecimal budget;
    @Enumerated
    private Currency currency;
    @ManyToOne
    @JoinColumn(name = "company_id")
    Company company;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "techjob_applicant",
            joinColumns = @JoinColumn(name = "techjob_id"),
            inverseJoinColumns = @JoinColumn(name = "applicant_id")
    )
    private Set<Applicant> applicants = new HashSet<>();
}
