package com.pixaica.security.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginUserDto {
    private String username;
    private String password;
}
