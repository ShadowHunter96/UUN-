package com.example.fullstack_backend.controller;

import com.example.fullstack_backend.model.LinkDTO;
import com.example.fullstack_backend.model.LinkEntity;
import com.example.fullstack_backend.service.LinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/save")
    public ResponseEntity<LinkDTO> createLink(@RequestBody LinkDTO linkDTO) {
        LinkDTO savedLink = linkService.saveLink(linkDTO);
        return ResponseEntity.ok(savedLink);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LinkDTO> updateLink(@PathVariable Long id, @RequestBody LinkDTO linkDTO) {
        linkDTO.setId(id);
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
