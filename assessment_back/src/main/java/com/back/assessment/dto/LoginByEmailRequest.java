package com.back.assessment.dto;

import lombok.Data;

@Data
public class LoginByEmailRequest {
    private String email;
    private String password;
}
