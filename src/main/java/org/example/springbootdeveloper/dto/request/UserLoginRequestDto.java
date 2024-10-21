package org.example.springbootdeveloper.dto.request;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserLoginRequestDto {
    private String username;
    private String password;
}
