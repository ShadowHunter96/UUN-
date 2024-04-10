package com.example.fullstack_backend.repository;

import com.example.fullstack_backend.model.LinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by User: Vu
 * Date: 10.04.2024
 * Time: 17:01
 */

public interface LinkRepository extends JpaRepository<LinkEntity,Long> {
    List<LinkEntity> findByAvailableInFirefoxAndAvailableInChromeAndIsActive(boolean availableInFirefox, boolean availableInChrome, boolean isActive);

}
