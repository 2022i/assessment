package com.back.assessment.dto;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    String email;
    String password;
    String code;
}
