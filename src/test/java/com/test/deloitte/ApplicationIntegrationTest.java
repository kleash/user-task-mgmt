package com.test.deloitte;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationIntegrationTest {

	@Autowired
	WebApplicationContext wac;
	@Autowired
	private TestRestTemplate template;

	@Test
	public void whenConfigured_thenLoadsContext() {
		assertNotNull(wac);
	}

	@Test
	public void authRequestSuccess() throws Exception {
		ResponseEntity<String> result = template.withBasicAuth("user1", "password").getForEntity("/index",
				String.class);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

}
