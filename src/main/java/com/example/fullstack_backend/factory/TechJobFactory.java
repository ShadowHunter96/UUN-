package com.example.fullstack_backend.factory;

import com.example.fullstack_backend.model.TechJob;
import com.example.fullstack_backend.model.TechJobDto;

import java.util.HashSet;

/**
 * Created by User: Vu
 * Date: 03.06.2024
 * Time: 18:53
 */
public class TechJobFactory {
    private TechJobFactory() {}

    // Převádí entitu TechJob na TechJobDto
    public static TechJobDto fromEntity(TechJob techJob) {
        if (techJob == null) {
            return null;
        }
        TechJobDto dto = new TechJobDto();
        dto.setId(techJob.getId());
        dto.setName(techJob.getName());
        dto.setBaitText(techJob.getBaitText());
        dto.setDescription(techJob.getDescription());
        dto.setSeniority(techJob.getSeniority());
        dto.setEducation(techJob.getEducation());
        dto.setCity(techJob.getCity());
        dto.setBudget(techJob.getBudget());
        dto.setCurrency(techJob.getCurrency());
        dto.setCompany(techJob.getCompany());  // Předpokládá se, že Company je také DTO nebo Entity
        dto.setApplicants(new HashSet<>(techJob.getApplicants()));  // Předpokládá se, že toto je povoleno
        dto.setApproved(techJob.isApproved());
        return dto;
    }

    // Naplňuje existující entitu TechJob z TechJobDto
    public static void fillEntity(TechJob techJob, TechJobDto dto) {
        if (techJob != null && dto != null) {
            techJob.setId(dto.getId());
            techJob.setName(dto.getName());
            techJob.setBaitText(dto.getBaitText());
            techJob.setDescription(dto.getDescription());
            techJob.setSeniority(dto.getSeniority());
            techJob.setEducation(dto.getEducation());
            techJob.setCity(dto.getCity());
            techJob.setBudget(dto.getBudget());
            techJob.setCurrency(dto.getCurrency());
            techJob.setCompany(dto.getCompany());  // Předpokládá se, že Company je správně zpracováno
            techJob.setApplicants(new HashSet<>(dto.getApplicants()));  // Předpokládá se, že toto je povoleno
            techJob.setApproved(dto.isApproved());
        }
    }
}

