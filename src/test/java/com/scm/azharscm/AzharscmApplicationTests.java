package com.scm.azharscm;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.scm.services.EmailService;

import config.Autowired;

@SpringBootTest
class AzharscmApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private EmailService service;

	@Test
	void sendEmailTest(){
		service.sendEmail("mirzaazhar.azhar44@gmail.com","just testing email",
			"this is azaharscm project working on email service");
	}

}
