package ca.mcgill.ecse321.webservice.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.awt.List;
import java.util.Arrays;
import java.util.Optional;

import org.aopalliance.intercept.Invocation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.webservice.model.User;
import ca.mcgill.ecse321.webservice.service.SignupService;
import ca.mcgill.ecse321.webservice.service.UserService;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignupControllerTest {

	@LocalServerPort
	private int port;
	
	@Mock
	private SignupService signupDAO;
	@InjectMocks
	private SignupController controller = new SignupController();
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	
	HttpHeaders headers = new HttpHeaders();
	
	private static double ratingTolerance = 0.05;
	
	private int updateCountId = 0;
	private int addCountId = 0;
	
	
	@Test
	public void simpleTest() {
		assertTrue(true);
	}
	
	@Test 
	public void signupUser() {
			User user = new User("Karlo", "Karlo", "pass", 3, 3);
			ResponseEntity response = controller.signup(user);
			Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	@Test
	public void signupInvalidUser() {
		User user = new User();
		
		ResponseEntity response = controller.signup(user);
		
		Assert.assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
	}

}
	

