package com.example.fullstack_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Created by User: Vu
 * Date: 10.04.2024
 * Time: 16:48
 */
@Entity
@Table(name ="link_entity")
@NoArgsConstructor
@AllArgsConstructor
public class LinkEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String url;
    @Lob
    private byte[] imageData;
    private String description;
    private boolean availableInFirefox;
    private boolean availableInChrome;
    private boolean isActive;
    private boolean openInNewWindow;


    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailableInFirefox() {
        return availableInFirefox;
    }

    public void setAvailableInFirefox(boolean availableInFirefox) {
        this.availableInFirefox = availableInFirefox;
    }

    public boolean isAvailableInChrome() {
        return availableInChrome;
    }

    public void setAvailableInChrome(boolean availableInChrome) {
        this.availableInChrome = availableInChrome;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isOpenInNewWindow() {
        return openInNewWindow;
    }

    public void setOpenInNewWindow(boolean openInNewWindow) {
        this.openInNewWindow = openInNewWindow;
    }
}
