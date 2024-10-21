package org.example.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.dto.request.UserLoginRequestDto;
import org.example.springbootdeveloper.dto.request.UserRequestDto;
import org.example.springbootdeveloper.dto.response.ResponseDto;
import org.example.springbootdeveloper.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseDto<String> signup(@RequestBody UserRequestDto dto) {
        try {
            String result = userService.signup(dto);
            return ResponseDto.setSuccess("signup successful", result);
        } catch (Exception e) {
            return ResponseDto.setFailed("Sign up Failed");
        }
    }

    @PostMapping("/login")
    public ResponseDto<String> login(@RequestBody UserLoginRequestDto dto){
        try {
            String result = userService.login(dto);
            return ResponseDto.setSuccess("signup successful", result);
        } catch (Exception e) {
            return ResponseDto.setFailed("Log in Failed" + e.getMessage());
        }
    }

}
