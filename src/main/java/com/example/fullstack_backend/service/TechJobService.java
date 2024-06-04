package com.example.fullstack_backend.service;

import com.example.fullstack_backend.commons.ConvertEntities;
import com.example.fullstack_backend.factory.TechJobFactory;
import com.example.fullstack_backend.factory.UserFactory;
import com.example.fullstack_backend.model.TechJob;
import com.example.fullstack_backend.model.TechJobDao;
import com.example.fullstack_backend.model.TechJobDto;
import com.example.fullstack_backend.model.TechJobFilterDto;
import com.example.fullstack_backend.repository.TechJobRepository;
import jakarta.transaction.SystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by User: Vu
 * Date: 03.06.2024
 * Time: 20:29
 */
@Service
public class TechJobService {
    private TechJobRepository techJobRepository;
    private TechJobDao techJobDao;

    public TechJobService(TechJobRepository techJobRepository, TechJobDao techJobDao) {
        this.techJobRepository = techJobRepository;
        this.techJobDao = techJobDao;
    }

    public List<TechJobDto> findJobs(TechJobFilterDto techJobFilterDto){
        return techJobDao.findTechJobs(techJobFilterDto);
    }

    public TechJobDto findTechJob(Long id) throws SystemException {
        TechJob techJob = techJobRepository.findById(id).orElseThrow(()->new RuntimeException("Entity with id " + id + " not found"));
        TechJobDto dto = TechJobFactory.fromEntity(techJob);
        return dto;
    }

    public TechJob saveTechJob(TechJobDto techJobDto){
        TechJob entity = new TechJob();
        TechJobFactory.fillEntity(entity,techJobDto);
        techJobRepository.save(entity);
        return entity;
    }

    public TechJobDto updateTechJob(Long id,TechJobDto techJobDto) throws RuntimeException {
        TechJob updatedEntity = new TechJob();
        if (!techJobRepository.existsById(id)) {
            throw new RuntimeException("TechJob with id " + techJobDto.getId() + " not found");
        }
        TechJobFactory.fillEntity(updatedEntity,techJobDto);
        updatedEntity.setId(id);
        techJobRepository.save(updatedEntity);
        return TechJobFactory.fromEntity(updatedEntity);
    }

    public String deleteTechJob(Long id){
        if (!techJobRepository.existsById(id)) {
            throw new RuntimeException("TechJob with id " + id.toString() + " not found");
        }
        techJobRepository.deleteById(id);
        String message = "TechJob with id: " + id + " deleted.";
        return message;
    }

    public String approveTechJob(Long id){
        TechJob techJob = techJobRepository.findById(id).orElseThrow(()->new RuntimeException("Entity with id " + id + " not found"));
        techJob.setApproved(true);
        techJobRepository.save(techJob);
        String message = "techJob with id "+ id +"approved";
        return message;
    }

    public String declineTechJob(Long id){
        TechJob techJob = techJobRepository.findById(id).orElseThrow(()->new RuntimeException("Entity with id " + id + " not found"));
        techJob.setApproved(false);
        techJobRepository.save(techJob);
        String message = "techJob with id "+ id +"rejected";
        return message;
    }

    public List<TechJobDto>getApprovedTechJobs(){
        return ConvertEntities.fromEntities(techJobRepository.findByApprovedTrue(), TechJobFactory::fromEntity);
    }

    public List<TechJobDto> findAllTechJobs(){
        return ConvertEntities.fromEntities(techJobRepository.findAll(), TechJobFactory::fromEntity);
    }

    public TechJobDto findDtoById(Long id){
        TechJob techJob = techJobRepository.findById(id).orElseThrow(()->new RuntimeException("Entity with id " + id + " not found"));
        return TechJobFactory.fromEntity(techJob);
    }

















}
