package com.edmond.securtiy.controller;

import com.edmond.securtiy.constant.Role;
import com.edmond.securtiy.dto.JwtTokenDto;
import com.edmond.securtiy.dto.RegisterDto;
import com.edmond.securtiy.entity.User;
import com.edmond.securtiy.service.AuthenticationService;
import com.edmond.securtiy.service.JwtService;
import com.edmond.securtiy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<JwtTokenDto> register(@RequestBody RegisterDto request){
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(Role.valueOf(request.getRole().toUpperCase()))
                .build();

        return ResponseEntity.ok(authenticationService.register(user));
    }

    @GetMapping("/alluser")
    public ResponseEntity<Object> getAllUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
        }

        if (token != null) {
            String role = jwtService.extractUserRole(token);
            if (role.equals(Role.ADMIN.toString())) {
                List<User> userList = userService.findUserList();
                return ResponseEntity.ok(userList);
            }
        }

        return ResponseEntity.ok("Invalid or missing token");

    }
}
