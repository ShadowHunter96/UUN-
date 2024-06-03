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
 * Date: 03.06.2024
 * Time: 18:57
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TechJobDto {
    private Long id;
    private String name;
    private String baitText;
    private String description;
    private Seniority seniority;
    private Education education;
    private City city;
    private BigDecimal budget;
    private Currency currency;
    Company company;
    private Set<Applicant> applicants = new HashSet<>();
    private boolean approved = false;

}