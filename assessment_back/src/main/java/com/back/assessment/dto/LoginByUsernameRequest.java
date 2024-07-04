package com.back.assessment.dto;

import lombok.Data;

@Data
public class LoginByUsernameRequest {
    private String username;
    private String password;
}
