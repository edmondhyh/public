package com.edmond.securtiy.service;

import com.edmond.securtiy.dto.JwtTokenDto;
import com.edmond.securtiy.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtService jwtService;
    private final UserService userService;

    public JwtTokenDto register(User user){
        userService.addUser(user);
        String token = jwtService.generateToken(user);
        JwtTokenDto jwtTokenDto = new JwtTokenDto();
        jwtTokenDto.setToken(token);
        jwtTokenDto.setExpiration(jwtService.getTokenExpiration());
        return jwtTokenDto;
    }
}
