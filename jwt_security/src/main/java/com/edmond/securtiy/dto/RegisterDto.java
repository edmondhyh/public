package com.edmond.securtiy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    private String name;
    private String email;
    private String password;
    private String role;
}
