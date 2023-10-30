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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/page")
@RequiredArgsConstructor
public class UserPageController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public String register(@ModelAttribute("registrationResult") String registrationResult, @RequestBody RegisterDto request, Model model){
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(Role.valueOf(request.getRole().toUpperCase()))
                .build();

        String resultMessage = authenticationService.register(user).toString();

        model.addAttribute("registrationResult", resultMessage);

        return "register";
    }

//    @GetMapping("/alluser")
//    public ResponseEntity<Object> getAllUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
//        String token = null;
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            token = authorizationHeader.substring(7);
//        }
//
//        if (token != null) {
//            String role = jwtService.extractUserRole(token);
//            if (role.equals(Role.ADMIN.toString())) {
//                List<User> userList = userService.findUserList();
//                return ResponseEntity.ok(userList);
//            }
//        }
//
//        return ResponseEntity.ok("Invalid or missing token");
//
//    }
}
