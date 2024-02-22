package com.example.fullstack_backend.factory;

import com.example.fullstack_backend.model.User;
import com.example.fullstack_backend.model.UserDTO;

/**
 * Created by User: Vu
 * Date: 06.02.2024
 * Time: 20:51
 */
public class UserFactory {
    private UserFactory(){}
    public static UserDTO fromEntity(User user ){
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public static void fillEntity(User user, UserDTO dto){
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
    }

}
