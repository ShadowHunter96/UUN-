package com.example.fullstack_backend.model;

import com.example.fullstack_backend.enums.City;
import com.example.fullstack_backend.enums.Currency;
import com.example.fullstack_backend.enums.Education;
import com.example.fullstack_backend.enums.Seniority;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by User: Vu
 * Date: 03.06.2024
 * Time: 19:40
 */
@Getter
@Setter
public class TechJobFilterDto {
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
    private Boolean approved;

    private int page;
    private int size;
    private List<Sort.Order> orderList;

}
