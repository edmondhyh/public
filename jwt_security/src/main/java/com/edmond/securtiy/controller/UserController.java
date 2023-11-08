package com.edmond.securtiy.controller;

import com.edmond.securtiy.constant.Role;
import com.edmond.securtiy.dto.JwtTokenDto;
import com.edmond.securtiy.dto.RegisterDto;
import com.edmond.securtiy.entity.User;
import com.edmond.securtiy.service.AuthenticationService;
import com.edmond.securtiy.service.JwtService;
import com.edmond.securtiy.service.UserService;
import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "User")
//@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final JwtService jwtService;

    @Operation(
            description = "create a new user",
            summary = "generate an unique token for permissions",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Duplicated email",
                            responseCode = "403"
                    )
            }
    )
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

//    @Hidden
    @GetMapping("/all")
    public ResponseEntity<Object> findAllUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = getToken(authorizationHeader);

        if (token != null) {
            String role = jwtService.extractUserRole(token);
            if (role.equals(Role.ADMIN.toString())) {
                List<User> userList = userService.findUserList();
                return ResponseEntity.ok(userList);
            }
        }

        return ResponseEntity.ok("Invalid or missing token");
    }

    @GetMapping("/user")
    public ResponseEntity<Object> findUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @RequestParam(name = "email") String email) {
        String token = getToken(authorizationHeader);

        if (token != null) {
            String jwtEmail = jwtService.extractUserEmail(token);
            if (jwtEmail.equals(email)) {
                return ResponseEntity.ok(userService.findByEmail(email));
            }
        }
        return ResponseEntity.ok("Token does not match with email");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestParam(name = "email") String email, @RequestParam(required = false, name = "name") String name, @RequestParam(required = false, name = "password") String password, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = getToken(authorizationHeader);

        if (token != null) {
            String jwtEmail = jwtService.extractUserEmail(token);
            if (jwtEmail.equals(email)) {
                User user = userService.findByEmail(email);
                if(user != null){
                    if( StringUtils.isNotEmpty(name)) user.setName(name) ;
                    if( StringUtils.isNotEmpty(password))  user.setPassword(password);
                    userService.updateUser(user);
                    return ResponseEntity.ok("User has been updated");
                }
            }
        }

        return ResponseEntity.ok("Token does not match with email");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam(name = "email") String email, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){
        String token = getToken(authorizationHeader);
        if(token != null){
            String jwtEmail = jwtService.extractUserEmail(token);
            if (jwtEmail.equals(email)) {
                userService.deleteUserByEmail(email);
                return ResponseEntity.ok("User has been deleted");
            }
        }
        return ResponseEntity.ok("Token does not match with email");
    }

    private String getToken(String authorizationHeader){
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}
