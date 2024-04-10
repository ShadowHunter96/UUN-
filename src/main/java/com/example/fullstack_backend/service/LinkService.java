package com.example.fullstack_backend.service;

import com.example.fullstack_backend.factory.LinkFactory;
import com.example.fullstack_backend.model.LinkDTO;
import com.example.fullstack_backend.model.LinkEntity;
import com.example.fullstack_backend.repository.LinkRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by User: Vu
 * Date: 10.04.2024
 * Time: 17:03
 */
@Service
public class LinkService {
    private LinkRepository linkRepository;

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public List<LinkDTO> getAllLinks() {
        List<LinkEntity> links = linkRepository.findAll();
        List<LinkDTO> linkDTOS = links.stream().map(LinkFactory::fromEntity).collect(Collectors.toList());
        return linkDTOS;
    }


    public LinkDTO saveLink(LinkDTO dto) {

        LinkEntity entity = new LinkEntity();
        LinkFactory.fillEntity(entity, dto);
        LinkEntity savedEntity = linkRepository.save(entity);
        return LinkFactory.fromEntity(savedEntity);
    }

    public LinkDTO updateLink(LinkDTO dto) {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("Link ID must not be null for update");
        }
        Optional<LinkEntity> existingEntityOpt = linkRepository.findById(dto.getId());
        if (!existingEntityOpt.isPresent()) {
            throw new EntityNotFoundException("Link with ID " + dto.getId() + " not found");
        }
        LinkEntity existingEntity = existingEntityOpt.get();
        LinkFactory.fillEntity(existingEntity, dto);
        LinkEntity savedEntity = linkRepository.save(existingEntity);
        return LinkFactory.fromEntity(savedEntity);
    }




    @Transactional
    public void deleteLink(Long id) {
        Optional<LinkEntity> linkEntityOptional = linkRepository.findById(id);
        if (linkEntityOptional.isPresent()) {
            LinkEntity linkEntity = linkEntityOptional.get();
            LinkFactory.deactivateLink(linkEntity);
            linkRepository.save(linkEntity);
        } else {
            throw new RuntimeException("Link with id: " + id + " not found.");
        }
    }
    public Optional<LinkDTO> findLinkById(Long id){
        Optional<LinkEntity> entityOptional = linkRepository.findById(id);
        if (entityOptional.isPresent()) {
            LinkEntity entity = entityOptional.get();
            LinkDTO dto = LinkFactory.fromEntity(entity);
            return Optional.of(dto);
        } else {
            return Optional.empty();
        }
    }

    public List<LinkEntity> getLinksByCriteria(boolean availableInFirefox, boolean availableInChrome, boolean isActive) {
        return linkRepository.findByAvailableInFirefoxAndAvailableInChromeAndIsActive(availableInFirefox, availableInChrome, isActive);
    }










}
