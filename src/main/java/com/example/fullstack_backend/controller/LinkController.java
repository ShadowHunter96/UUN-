package com.example.fullstack_backend.controller;

import com.example.fullstack_backend.model.LinkDTO;
import com.example.fullstack_backend.model.LinkEntity;
import com.example.fullstack_backend.service.LinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by User: Vu
 * Date: 10.04.2024
 * Time: 17:41
 */
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/links-api")
public class LinkController {
    LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/links")
    public ResponseEntity<List<LinkDTO>> getAllLinks() {
        List<LinkDTO> links = linkService.getAllLinks();
        return new ResponseEntity<>(links, HttpStatus.OK);
    }
    @PostMapping(path = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LinkDTO> createLink(
            @RequestParam("name") String name,
            @RequestParam("url") String url,
            @RequestParam("description") String description,
            @RequestParam("availableInFirefox") boolean availableInFirefox,
            @RequestParam("availableInChrome") boolean availableInChrome,
            @RequestParam("isActive") boolean isActive,
            @RequestParam("openInNewWindow") boolean openInNewWindow,
            @RequestPart("image") MultipartFile image) throws IOException, SQLException {

        LinkDTO linkDTO = new LinkDTO();
        linkDTO.setName(name);
        linkDTO.setUrl(url);
        linkDTO.setDescription(description);
        linkDTO.setAvailableInFirefox(availableInFirefox);
        linkDTO.setAvailableInChrome(availableInChrome);
        linkDTO.setActive(isActive);
        linkDTO.setOpenInNewWindow(openInNewWindow);

        if (image != null && !image.isEmpty()) {
            byte[] bytes = image.getBytes();
            Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
            linkDTO.setImage(blob);
        }

        LinkDTO savedLink = linkService.saveLink(linkDTO);
        return ResponseEntity.ok(savedLink);
    }

    @PutMapping(path = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LinkDTO> updateLink(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("url") String url,
            @RequestParam("description") String description,
            @RequestParam("availableInFirefox") boolean availableInFirefox,
            @RequestParam("availableInChrome") boolean availableInChrome,
            @RequestParam("isActive") boolean isActive,
            @RequestParam("openInNewWindow") boolean openInNewWindow,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException, SQLException {

        LinkDTO linkDTO = linkService.findLinkById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Link not found"));
        if (linkDTO == null) {
            return ResponseEntity.notFound().build();
        }

        linkDTO.setName(name);
        linkDTO.setUrl(url);
        linkDTO.setDescription(description);
        linkDTO.setAvailableInFirefox(availableInFirefox);
        linkDTO.setAvailableInChrome(availableInChrome);
        linkDTO.setActive(isActive);
        linkDTO.setOpenInNewWindow(openInNewWindow);

        if (image != null && !image.isEmpty()) {
            byte[] bytes = image.getBytes();
            Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
            linkDTO.setImage(blob);
        }

        LinkDTO updatedLink = linkService.updateLink(linkDTO);
        return ResponseEntity.ok(updatedLink);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLink(@PathVariable Long id) {
        linkService.deleteLink(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/link/{id}")
    public ResponseEntity<LinkDTO> getLinkById(@PathVariable Long id) {
        Optional<LinkDTO> linkDTO = linkService.findLinkById(id);
        return linkDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

