package com.back.assessment.service;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;

public interface EncodingSelectionService {
    Map<String, PasswordEncoder> setEncryptedMap();
    PasswordEncoder getEncoder();
}
