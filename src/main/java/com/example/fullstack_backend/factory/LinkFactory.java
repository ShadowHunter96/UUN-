package com.example.fullstack_backend.factory;

import com.example.fullstack_backend.model.LinkDTO;
import com.example.fullstack_backend.model.LinkEntity;

/**
 * Created by User: Vu
 * Date: 10.04.2024
 * Time: 19:27
 */
public class LinkFactory {
    public LinkFactory() {
    }

    public static LinkDTO fromEntity(LinkEntity link ){
        LinkDTO dto = new LinkDTO();
        dto.setId(link.getId());
        dto.setName(link.getName());
        dto.setUrl(link.getUrl());
        dto.setImageData(link.getImageData());
        dto.setDescription(link.getDescription());
        dto.setAvailableInFirefox(link.isAvailableInFirefox());
        dto.setAvailableInChrome(link.isAvailableInChrome());
        dto.setActive(link.isActive());
        dto.setOpenInNewWindow(link.isOpenInNewWindow());
        return dto;
    }

    public static void fillEntity(LinkEntity link, LinkDTO dto){
        link.setId(dto.getId());
        link.setName(dto.getName());
        link.setUrl(dto.getUrl());
        link.setImageData(dto.getImageData());
        link.setDescription(dto.getDescription());
        link.setAvailableInFirefox(dto.isAvailableInFirefox());
        link.setAvailableInChrome(dto.isAvailableInChrome());
        link.setActive(dto.isActive());
        link.setOpenInNewWindow(dto.isOpenInNewWindow());
    }

    public static void deactivateLink(LinkEntity link) {
        link.setActive(false);
    }


}
