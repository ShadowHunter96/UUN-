package com.example.fullstack_backend.service;

import com.example.fullstack_backend.commons.ConvertEntities;
import com.example.fullstack_backend.factory.TechJobFactory;
import com.example.fullstack_backend.model.TechJob;
import com.example.fullstack_backend.model.TechJobDao;
import com.example.fullstack_backend.model.TechJobDto;
import com.example.fullstack_backend.model.TechJobFilterDto;
import com.example.fullstack_backend.repository.TechJobRepository;
import jakarta.transaction.SystemException;
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












}
