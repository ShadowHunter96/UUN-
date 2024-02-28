package com.example.fullstack_backend.controller;

import com.example.fullstack_backend.model.TechJob;
import com.example.fullstack_backend.model.User;
import com.example.fullstack_backend.repository.TechJobRepository;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by User: Vu
 * Date: 22.02.2024
 * Time: 20:17
 */
@RestController
@CrossOrigin("http://localhost:3000")
public class TechJobController {
    TechJobRepository techJobRepository;
    public TechJobController(TechJobRepository techJobRepository){
        this.techJobRepository = techJobRepository;
    }
    @PostMapping("/techJob")
    public ResponseEntity<?>saveTechJob(@RequestBody TechJob techJob){
        try {
            techJobRepository.save(techJob);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (RuntimeException e){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/techJobs/batch")
    public ResponseEntity<?> saveTechJobs(@RequestBody List<TechJob> techJobs) {
        for (TechJob techJob : techJobs) {
            // Set the ID to null to let the database generate a new ID
            techJob.setId(null);

            // Save the techJob
            techJobRepository.save(techJob);
        }
        return new ResponseEntity<>(techJobs,HttpStatus.CREATED);
    }

    @GetMapping("/techJobs")
    public ResponseEntity<List<TechJob>> getAllTechJobs() {
        List<TechJob> techJobs = techJobRepository.findAll();
        return new ResponseEntity<>(techJobs, HttpStatus.OK);
    }
    @GetMapping("/techJob/{id}")
    public ResponseEntity<TechJob> getTechJobById(@PathVariable Long id) {
        Optional<TechJob> techJobOptional = techJobRepository.findById(id);
        return techJobOptional.map(techJob -> new ResponseEntity<>(techJob, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/techJob/{id}")
    public ResponseEntity<?> updateTechJob(@PathVariable Long id, @RequestBody TechJob updatedTechJob) {
        if (!techJobRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        updatedTechJob.setId(id);
        techJobRepository.save(updatedTechJob);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/techJob/{id}")
    public ResponseEntity<?> deleteTechJob(@PathVariable Long id) {
        if (!techJobRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        techJobRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
