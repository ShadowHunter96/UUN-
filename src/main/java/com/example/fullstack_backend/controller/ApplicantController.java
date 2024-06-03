package com.example.fullstack_backend.controller;

import com.example.fullstack_backend.model.Applicant;
import com.example.fullstack_backend.model.TechJob;
import com.example.fullstack_backend.repository.ApplicantRepository;
import com.example.fullstack_backend.repository.TechJobRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    private final TechJobRepository techJobRepository;

    public ApplicantController(ApplicantRepository applicantRepository, TechJobRepository techJobRepository) {
        this.applicantRepository = applicantRepository;
        this.techJobRepository = techJobRepository;
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
        Applicant applicant = applicantRepository.findById(id).orElseThrow(()->new RuntimeException("applicant not found"));
        applicant.setDeleted(true);

        return new ResponseEntity<>(applicant, HttpStatus.OK);
    }

    @PostMapping("/{applicantId}/apply/{techJobId}")
    public ResponseEntity<?> applyToTechJob(@PathVariable Long applicantId, @PathVariable Long techJobId) {
        Optional<Applicant> applicantOptional = applicantRepository.findById(applicantId);
        Optional<TechJob> techJobOptional = techJobRepository.findById(techJobId);

        if (applicantOptional.isPresent() && techJobOptional.isPresent()) {
            Applicant applicant = applicantOptional.get();
            TechJob techJob = techJobOptional.get();

            // Propojení entit
            techJob.getApplicants().add(applicant);
            applicant.getTechJobs().add(techJob);

            // Uložení změn
            techJobRepository.save(techJob);
            applicantRepository.save(applicant);

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Applicant or TechJob not found", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/applicant/search/by-name-and-surname")
    public Optional<Long> getApplicantIdByNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        Optional<Long> applicantID = applicantRepository.findApplicantIdByNameAndSurname(name, surname);
        return applicantID;
    }

    @GetMapping("/search/by-email")
    public ResponseEntity<Long> getApplicantIdByEmail(@RequestParam String email) {
        return applicantRepository.findApplicantByEmail(email)
                .map(applicant -> ResponseEntity.ok(applicant.getId()))
                .orElse(ResponseEntity.notFound().build());
    }


}
