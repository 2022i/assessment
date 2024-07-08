package com.back.assessment;

import com.back.assessment.service.EncodingSelectionService;
import com.back.assessment.service.impl.MailServiceImpl;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class AssessmentApplicationTests {
	@Resource
	private MailServiceImpl mailServiceImpl;

	@Resource
	private EncodingSelectionService encodingSelectionService;

	@Test
	void testMail() {
		mailServiceImpl.mailSendForRegister("2022213356@mail.hfut.edu.cn");
	}

	@Test
	void testEncoder(){
		PasswordEncoder passwordEncoder = encodingSelectionService.getEncoder();
		String password="666";
		String password2 = passwordEncoder.encode(password);
		System.out.println(password2);
		System.out.println(passwordEncoder.matches(password,password2));
	}

}
