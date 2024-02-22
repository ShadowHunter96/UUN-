package com.example.fullstack_backend.controller;

import com.example.fullstack_backend.model.Company;
import com.example.fullstack_backend.repository.CompanyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by User: Vu
 * Date: 22.02.2024
 * Time: 20:49
 */
@RestController
@CrossOrigin("http://localhost:3000")
public class CompanyController {
    private final CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    // POST method to create a new Company
    @PostMapping("/companies")
    public ResponseEntity<?> createCompany(@RequestBody Company company) {
        if (companyRepository.existsById(company.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        companyRepository.save(company);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("/companies/batch")
    public ResponseEntity<?> createCompanies(@RequestBody List<Company> companies) {
        for (Company company : companies) {
            // Set the ID to null to let the database generate a new ID
            company.setId(null);

            // Save the company
            companyRepository.save(company);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    // GET method to retrieve all Companies
    @GetMapping("/companies")
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    // GET method to retrieve a Company by ID
    @GetMapping("/companies/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        return companyOptional.map(company -> new ResponseEntity<>(company, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // PUT method to update an existing Company
    @PutMapping("/companies/{id}")
    public ResponseEntity<?> updateCompany(@PathVariable Long id, @RequestBody Company updatedCompany) {
        if (!companyRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        updatedCompany.setId(id);
        companyRepository.save(updatedCompany);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // DELETE method to delete a Company by ID
    @DeleteMapping("/companies/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable Long id) {
        if (!companyRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        companyRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
