package com.back.assessment.service.impl;

import com.back.assessment.dto.Encryption;
import com.back.assessment.service.EncodingSelectionService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("EncodingSelectionService")
public class EncodingSelectionServiceImpl implements EncodingSelectionService {
    @Override
    public Map<String,PasswordEncoder> setEncryptedMap() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("pbkdf2", Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8());
        encoders.put("scrypt", SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8());
        encoders.put("argon2", Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8());
        return encoders;
    }

    @Override
    public PasswordEncoder getEncoder() {
        String idForEncode =  Encryption.idForEncode;
        Map<String,PasswordEncoder> encoders = setEncryptedMap();
        return new DelegatingPasswordEncoder(idForEncode, encoders);
    }

}
