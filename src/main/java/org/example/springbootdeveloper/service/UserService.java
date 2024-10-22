package org.example.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.dto.request.UserLoginRequestDto;
import org.example.springbootdeveloper.dto.request.UserRequestDto;
import org.example.springbootdeveloper.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String signup(UserRequestDto dto) {
        return null;
    }

    public String login(UserLoginRequestDto dto) {
        try {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
