package com.edu.uptc.loginService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
    "RESEND_API_KEY=dummy",
    "APP_ADMIN_EMAIL=test@example.com"
})
class LoginServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
