package com.back.assessment;

import com.back.assessment.mail.MailMail;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AssessmentApplicationTests {
	@Resource
	private MailMail mailMail;

	@Test
	void contextLoads() {
		mailMail.mailSend("2022213356@mail.hfut.edu.cn");
	}

}
