package com.example.fullstack_backend.controller;

import com.example.fullstack_backend.model.Applicant;
import com.example.fullstack_backend.repository.ApplicantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User: Vu
 * Date: 29.02.2024
 * Time: 12:42
 */
@RestController
@RequestMapping("/applicant")
@CrossOrigin("http://localhost:3000")
public class ApplicantController {
    private final ApplicantRepository applicantRepository;

    public ApplicantController(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveApplicant(@RequestBody Applicant applicant) {
        try {
            applicantRepository.save(applicant);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/batch")
    public ResponseEntity<?> saveApplicants(@RequestBody List<Applicant> applicants) {
        for (Applicant applicant : applicants) {
            // Set the ID to null to let the database generate a new ID
            applicant.setId(null);

            // Save the applicant
            applicantRepository.save(applicant);
        }
        return new ResponseEntity<>(applicants, HttpStatus.CREATED);
    }

    @GetMapping("/applicant-list")
    public ResponseEntity<List<Applicant>> getAllApplicants() {
        List<Applicant> allApplicants = applicantRepository.findAll();
        return new ResponseEntity<>(allApplicants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Applicant> getApplicantById(@PathVariable Long id) {
        return applicantRepository.findById(id)
                .map(applicant -> new ResponseEntity<>(applicant, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateApplicant(@PathVariable Long id, @RequestBody Applicant updatedApplicant) {
        return applicantRepository.findById(id)
                .map(existingApplicant -> {
                    existingApplicant.setName(updatedApplicant.getName());
                    existingApplicant.setSurname(updatedApplicant.getSurname());
                    existingApplicant.setEmail(updatedApplicant.getEmail());
                    existingApplicant.setPhoneNumber(updatedApplicant.getPhoneNumber());
                    existingApplicant.setSelfDescription(updatedApplicant.getSelfDescription());
                    existingApplicant.setLinkedInUrl(updatedApplicant.getLinkedInUrl());
                    applicantRepository.save(existingApplicant);
                    return new ResponseEntity<>(HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteApplicant(@PathVariable Long id) {
        return applicantRepository.findById(id)
                .map(applicant -> {
                    applicantRepository.delete(applicant);
                    return new ResponseEntity<>(HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
