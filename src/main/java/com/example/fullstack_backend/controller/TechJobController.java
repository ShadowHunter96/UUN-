package com.example.fullstack_backend.controller;

import com.example.fullstack_backend.factory.TechJobFactory;
import com.example.fullstack_backend.model.TechJob;
import com.example.fullstack_backend.model.TechJobDto;
import com.example.fullstack_backend.model.TechJobFilterDto;
import com.example.fullstack_backend.model.User;
import com.example.fullstack_backend.repository.TechJobRepository;
import com.example.fullstack_backend.service.TechJobService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.data.domain.Sort;
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
    TechJobService techJobService;
    public TechJobController(TechJobService techJobService){
        this.techJobService = techJobService;
    }
    @PostMapping("/techJob")
    public ResponseEntity<?>saveTechJob(@RequestBody TechJobDto techJobDto){
        try {
            techJobService.saveTechJob(techJobDto);
            return new ResponseEntity<>(techJobDto,HttpStatus.CREATED);
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
            techJobService.saveTechJob(TechJobFactory.fromEntity(techJob));
        }
        return new ResponseEntity<>(techJobs,HttpStatus.CREATED);
    }

    @GetMapping("/techJobs")
    public ResponseEntity<List<TechJobDto>> getAllTechJobs() {
        List<TechJobDto> techJobs = techJobService.findAllTechJobs();
        return new ResponseEntity<>(techJobs, HttpStatus.OK);
    }
    @GetMapping("/techJob/{id}")
    public ResponseEntity<TechJobDto> getTechJobById(@PathVariable Long id) {
        TechJobDto dto =  techJobService.findDtoById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @PutMapping("/techJob/{id}")
    public ResponseEntity<?> updateTechJob(@PathVariable Long id, @RequestBody TechJobDto updatedTechJob) {
        TechJobDto dto =  techJobService.updateTechJob(id,updatedTechJob);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @DeleteMapping("/techJob/{id}")
    public ResponseEntity<?> deleteTechJob(@PathVariable Long id) {

        String message =  techJobService.deleteTechJob(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping("/techJob/{id}/approve")
    public ResponseEntity<?> approveTechJob(@PathVariable Long id) {
        String message = techJobService.approveTechJob(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/techJob/{id}/decline")
    public ResponseEntity<?> declineTechJob(@PathVariable Long id) {

        String message = techJobService.declineTechJob(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/approved")
    public ResponseEntity<List<TechJobDto>> getApprovedTechJobs() {
        List<TechJobDto> approvedTechJobs = techJobService.getApprovedTechJobs();
        return new ResponseEntity<>(approvedTechJobs, HttpStatus.OK);
    }
    @PostMapping("/filter")
    public ResponseEntity<List<TechJobDto>> findJobs(@RequestBody TechJobFilterDto techJobFilterDto) {
        // Ensure the orderList is not null and defaults to sorting by "id"
        if (techJobFilterDto.getOrderList() == null || techJobFilterDto.getOrderList().isEmpty()) {
            techJobFilterDto.setOrderList(List.of(Sort.Order.asc("id")));
        }

        // Map filter fields
        TechJobDto newFilter = new TechJobDto();
        newFilter.setName(techJobFilterDto.getName());
        newFilter.setBaitText(techJobFilterDto.getBaitText());
        newFilter.setDescription(techJobFilterDto.getDescription());
        newFilter.setSeniority(techJobFilterDto.getSeniority());
        newFilter.setEducation(techJobFilterDto.getEducation());
        newFilter.setCity(techJobFilterDto.getCity());
        newFilter.setBudget(techJobFilterDto.getBudget());

        // Fetch jobs using the service
        List<TechJobDto> jobs = techJobService.findJobs(techJobFilterDto);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }






}
