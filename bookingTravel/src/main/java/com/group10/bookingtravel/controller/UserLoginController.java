package com.group10.bookingtravel.controller;

import com.group10.bookingtravel.dto.*;
import com.group10.bookingtravel.entity.User;
import com.group10.bookingtravel.service.UserService;
import com.group10.bookingtravel.utils.jwt.JWTHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class UserLoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JWTHandler jwtHandler;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse> authenticateUser(@Valid @RequestBody UserLoginRequestDTO loginRequestDTO) {

        // Xác thực từ username và password.
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.getUsername(),
                            loginRequestDTO.getPassword()
                    )
            );
            // Nếu không xảy ra exception tức là thông tin hợp lệ
            // Set thông tin authentication vào Security Context
            UserJwtDTO userData = (UserJwtDTO) authentication.getPrincipal();
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Trả về jwt cho người dùng.
            String jwt = jwtHandler.generateToken((UserJwtDTO) authentication.getPrincipal());
            return ResponseEntity.ok(new BaseResponse(true, "Login success", new UserJwtResDTO(userData.getId(), jwt)));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(403).body(new BaseResponse(false, "Invalid Credential"));
        }
    }

    @PostMapping("/login-admin")
    public ResponseEntity<BaseResponse> registerUser(@Valid @RequestBody UserLoginRequestDTO loginRequestDTO) {

        // Xác thực từ username và password.
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.getUsername(),
                            loginRequestDTO.getPassword()
                    )
            );
            //check admin
            UserJwtDTO userData = (UserJwtDTO) authentication.getPrincipal();
            Boolean isAdmin = userData.getIsAdmin();
            if (isAdmin) {
                // Nếu không xảy ra exception tức là thông tin hợp lệ
                // Set thông tin authentication vào Security Context
                SecurityContextHolder.getContext().setAuthentication(authentication);
                // Trả về jwt cho người dùng.
                String jwt = jwtHandler.generateToken(userData);
                return ResponseEntity.ok(new BaseResponse(true, "Login success", new UserJwtResDTO(userData.getId(), jwt)));
            }
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(403).body(new BaseResponse(false, "Invalid Credential"));
        }
        return ResponseEntity.status(403).body(new BaseResponse(false, "Forbidden"));
    }

    @PostMapping("/register")
    public ResponseEntity<BaseResponse> registerUser(@Valid @RequestBody UserRegisterRequestDTO registerRequestDTO) {

        // check username exist?
        try {
            userService.loadUserByUsername(registerRequestDTO.getUsername());
            return ResponseEntity.badRequest().body(new BaseResponse(false, "username already exist"));
        } catch (UsernameNotFoundException e) {
            User user = new User();
            user.setUsername(registerRequestDTO.getUsername());
            user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
            user.setAddress(registerRequestDTO.getAddress());
            user.setFullname(registerRequestDTO.getFullname());
            user.setEmail(registerRequestDTO.getEmail());
            user.setGender(registerRequestDTO.getGender());
            user.setDob(registerRequestDTO.getDob());
            user.setCreatedDate(new Date());

            userService.saveUser(user);

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            registerRequestDTO.getUsername(),
                            registerRequestDTO.getPassword()
                    )
            );

            UserJwtDTO userData = (UserJwtDTO) authentication.getPrincipal();
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtHandler.generateToken(userData);

            return ResponseEntity.ok(new BaseResponse(true, "Register successfully", new UserJwtResDTO(userData.getId(), jwt)));
        }
    }
}