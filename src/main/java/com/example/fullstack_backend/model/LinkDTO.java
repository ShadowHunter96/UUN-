package com.example.fullstack_backend.model;

import jakarta.persistence.Lob;
import lombok.*;

import java.sql.Blob;

/**
 * Created by User: Vu
 * Date: 10.04.2024
 * Time: 19:28
 */

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LinkDTO {
    private Long id;
    private String name;
    private String url;
    @Lob
    private byte[] image;
    private String imageBase64;
    private String description;
    private boolean availableInFirefox;
    private boolean availableInChrome;
    private boolean isActive;
    private boolean openInNewWindow;
    private boolean deleted;
}
