package com.example.fullstack_backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;

/**
 * Created by User: Vu
 * Date: 10.04.2024
 * Time: 16:48
 */
@Entity
@Table(name ="link_entity")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LinkEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
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

}
