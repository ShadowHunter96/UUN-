package com.example.fullstack_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String selfDescription;
    private String linkedInUrl;

}
