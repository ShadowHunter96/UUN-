package com.example.fullstack_backend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by User: Vu
 * Date: 06.02.2024
 * Time: 20:46
 */

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String username;
    private String name;
    private String email;
}
