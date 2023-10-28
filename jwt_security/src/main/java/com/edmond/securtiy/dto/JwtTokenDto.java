package com.edmond.securtiy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtTokenDto {
    String token;
    Date expiration;
}
