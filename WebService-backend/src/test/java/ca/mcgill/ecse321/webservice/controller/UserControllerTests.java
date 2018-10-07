package ca.mcgill.ecse321.webservice.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.json.JSONException;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import ca.mcgill.ecse321.webservice.model.User;
import ca.mcgill.ecse321.webservice.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTests {
	
	@LocalServerPort
	private int port;
	
	@Mock
	private UserService userDAO;
	@InjectMocks
	private UserController controller = new UserController();
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	
	HttpHeaders headers = new HttpHeaders();
	
	private static double ratingTolerance = 0.05;
	
	@Before
	public void setMockOutput(){
		when(userDAO.getUser(ArgumentMatchers.anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
			Long arg = invocation.getArgument(0);
			
			
			if(arg == -1){
				return null;
			} else if (arg == 0) {
				Optional<User> user = Optional.of(new User("Karlo", "Karlo", "pass", 3, 3, null, null));
				return user;
			} else {
				return null;
			}
		  });
		
		when(userDAO.getUsers()).thenAnswer((InvocationOnMock invocation)->{
			
			
			return null;
		});
	}
	
	@Test
	public void simpleTest() {
		assertTrue(true);
	}
	
	@Test
	public void getExistingUser() {
		Optional<User> resp = (Optional<User>) controller.getUser(0).getBody();

		Optional<User> expected = Optional.of(new User("Karlo", "Karlo", "pass", 3, 3, null, null));
		
		assertUserEquals(expected.get(), resp.get());
	}
	
	@Test
	public void getNonExistingUser() {
		ResponseEntity<?> resp = controller.getUser(3);
		
		Assert.assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
	}
	
	@Test
	public void getInvalidUser() {
		ResponseEntity<?> resp = controller.getUser(-1);
		
		Assert.assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
	}
	
	public static void assertUserEquals(User expected, User actual) {
		Assert.assertEquals(expected.getName(), actual.getName());
		Assert.assertEquals(expected.getUsername(), actual.getUsername());
		Assert.assertEquals(expected.getPassword(), actual.getPassword());
		Assert.assertEquals(expected.getPassRate(), actual.getPassRate(), ratingTolerance);
		Assert.assertEquals(expected.getDrivingRate(), actual.getDrivingRate(), ratingTolerance);
	}
}
