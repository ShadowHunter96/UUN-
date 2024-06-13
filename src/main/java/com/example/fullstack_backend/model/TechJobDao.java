package com.example.fullstack_backend.model;

import com.example.fullstack_backend.commons.ConvertEntities;
import com.example.fullstack_backend.factory.TechJobFactory;
import com.example.fullstack_backend.repository.TechJobRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User: Vu
 * Date: 03.06.2024
 * Time: 18:41
 */

@Component
public class TechJobDao {

    private final TechJobRepository techJobRepository;

    public TechJobDao(TechJobRepository techJobRepository) {
        this.techJobRepository = techJobRepository;
    }

    public List<TechJobDto> findTechJobs(TechJobFilterDto techJobFilterDto) {
        List<TechJob> techJobEntities = techJobRepository.findAll(getTechJobSpecification(techJobFilterDto),
                Sort.by(techJobFilterDto.getOrderList()));
        return ConvertEntities.fromEntities(techJobEntities, TechJobFactory::fromEntity);
    }

    private Specification<TechJob> getTechJobSpecification(TechJobFilterDto filter) {
        return (Root<TechJob> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) ->
                criteriaBuilder.and(getTechJobPredicateList(filter, root, criteriaBuilder).toArray(new Predicate[0]));
    }

    private List<Predicate> getTechJobPredicateList(
            TechJobFilterDto filter, Root<TechJob> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("id"), filter.getId()));
        }
        if (filter.getName() != null) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + filter.getName() + "%"));
        }
        if (filter.getBaitText() != null) {
            predicates.add(criteriaBuilder.like(root.get("baitText"), "%" + filter.getBaitText() + "%"));
        }
        if (filter.getDescription() != null) {
            predicates.add(criteriaBuilder.like(root.get("description"), "%" + filter.getDescription() + "%"));
        }
        if (filter.getSeniority() != null) {
            predicates.add(criteriaBuilder.equal(root.get("seniority"), filter.getSeniority()));
        }
        if (filter.getEducation() != null) {
            predicates.add(criteriaBuilder.equal(root.get("education"), filter.getEducation()));
        }
        if (filter.getCity() != null) {
            predicates.add(criteriaBuilder.equal(root.get("city"), filter.getCity()));
        }
        if (filter.getBudget() != null) {
            predicates.add(criteriaBuilder.equal(root.get("budget"), filter.getBudget()));
        }
        if (filter.getCurrency() != null) {
            predicates.add(criteriaBuilder.equal(root.get("currency"), filter.getCurrency()));
        }
        if (filter.getCompany() != null) {
            predicates.add(criteriaBuilder.equal(root.get("company"), filter.getCompany()));
        }
        if (filter.getApproved() != null) {
            predicates.add(criteriaBuilder.equal(root.get("approved"), filter.getApproved()));
        }



        return predicates;
    }

    public Page<TechJobDto> findTechJobPage(TechJobFilterDto techJobFilterDto) {
        Page<TechJob> techJobEntityPage = techJobRepository.findAll(getTechJobSpecification(techJobFilterDto),
                PageRequest.of(techJobFilterDto.getPage(), techJobFilterDto.getSize(),
                        Sort.by(techJobFilterDto.getOrderList())));
        List<TechJobDto> techJobDtoList = ConvertEntities
                .fromEntities(techJobEntityPage.toList(), TechJobFactory::fromEntity);
        return new PageImpl<>(techJobDtoList, PageRequest.of(techJobFilterDto.getPage(),
                techJobFilterDto.getSize(), Sort.by(techJobFilterDto.getOrderList())),
                techJobEntityPage.getTotalElements());
    }
}
