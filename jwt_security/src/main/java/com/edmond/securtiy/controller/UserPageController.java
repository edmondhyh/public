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
    public String register(@ModelAttribute RegisterDto request, Model model){
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(Role.valueOf(request.getRole().toUpperCase()))
                .build();

        JwtTokenDto jwtTokenDto = authenticationService.register(user);

        model.addAttribute("token", jwtTokenDto.getToken());
        model.addAttribute("expiration", jwtTokenDto.getExpiration());

        return "register";
    }

    @GetMapping("/user")
    public String register(@RequestParam("token") String token, Model model){
        String role = jwtService.extractUserRole(token);
        if (role.equals(Role.ADMIN.toString())){
            List<User> userList = userService.findUserList();
            model.addAttribute("user", userList.toString());
        }else{
            String email = jwtService.extractUserEmail(token);
            User user = userService.findByEmail(email);
            model.addAttribute("user", user.toString());
        }
        return "main";
    }
}
