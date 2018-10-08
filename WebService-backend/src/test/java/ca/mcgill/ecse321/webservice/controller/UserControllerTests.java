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
	
	private int updateCountId = 0;
	private int addCountId = 0;
	
	@Before
	public void setMockOutput(){
		when(userDAO.getUser(ArgumentMatchers.anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
			Long arg = invocation.getArgument(0);
			
			
			if(arg == -1){
				return null;
			} else if (arg == 0) {
				Optional<User> user = Optional.of(new User("Karlo", "Karlo", "pass", 3, 3));
				return user;
			} else {
				return null;
			}
		  });
		
		when(userDAO.getUsers()).thenAnswer((InvocationOnMock invocation)->{
			User user1 = new User("Brendan", "Brend", "1234", 3.5f, 4.0f);
			User user2 = new User("Michel", "mich", "5678", 4.5f, 4.5f);
			
			User[] userArr = {user1, user2};
			
			Iterable<User> userList = Arrays.asList(userArr);
			
			return null;
		});
		
		when(userDAO.addUser(ArgumentMatchers.any())).thenAnswer((InvocationOnMock invocation)->{
			User arg = invocation.getArgument(0);
			
			if(arg == null) {
				return null;
			}
			
			if(arg.getId() == null) {
				arg.setId(new Long(addCountId++));
			}
			
			return arg;
			
		});
		
		when(userDAO.updateUser(ArgumentMatchers.anyLong(), ArgumentMatchers.any())).thenAnswer((InvocationOnMock invocation) ->{
			User arg = invocation.getArgument(1);
			
			if(arg == null) {
				return null;
			}
			
			if(arg.getId() == null) {
				arg.setId(new Long(updateCountId++));
			}
			
			return arg;
		});
	}
	
	/**
	 * Simple test for verifying junit is running correctly
	 */
	@Test
	public void simpleTest() {
		assertTrue(true);
	}
	
	//getUser tests
	
	/**
	 * Get a user that exists and make sure it the same.
	 */
	@Test
	public void getExistingUser() {
		Optional<User> resp = (Optional<User>) controller.getUser(0).getBody();

		Optional<User> expected = Optional.of(new User("Karlo", "Karlo", "pass", 3, 3));
		
		assertUserEquals(expected.get(), resp.get());
	}
	
	/**
	 * Get a user that does not exist and check for a NOT_FOUND status message
	 */
	@Test
	public void getNonExistingUser() {
		ResponseEntity<?> resp = controller.getUser(3);
		
		Assert.assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
	}
	
	/**
	 * Sends an invalid user id and checks for a BAD_REQUEST status message
	 */
	@Test
	public void getInvalidUser() {
		ResponseEntity<?> resp = controller.getUser(-1);
		
		Assert.assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
	}
	
	//addUser tests
	
	/**
	 * Send a valid user to be added
	 */
	@Test
	public void addValidUser() {
		User user = new User("Karlo", "Karlo", "pass", 3, 3);
		
		User response = (User) controller.addUser(user).getBody();
		
		assertUserEquals(user, response);
	}
	
	@Test
	public void checkDriverUpdateRating() {
		User user = new User("Karlo", "Karlo", "pass", 3, 3);
		user.updateDriverRating(3);
		Assert.assertEquals(3, user.getDrivingRate(), 0);
	}
	@Test
	public void checkPassengerUpdateRating() {
		User user = new User("Karlo", "Karlo", "pass", 3, 3);
		user.updatePassRating(3);
		Assert.assertEquals(3, user.getPassRate(), 0);
	}
	/**
	 * Send an invalid user and checks for a NOT_ACCEPTABLE status message
	 */
	@Test
	public void addInvalidUser() {
		User user = new User();
		
		ResponseEntity response = controller.addUser(user);
		
		Assert.assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
	}
	
	/**
	 * Sends a null user and checks for a NOT_ACCEPTABLE status message
	 */
	@Test
	public void addNullUser() {
		ResponseEntity response = controller.addUser(null);
		
		Assert.assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
	}
	
	
	/**
	 * Updates with a valid user
	 */
	@Test
	public void updateWithValidUser() {
		User user = new User("Karlo", "Karlo", "pass", 3, 3);
		
		User response = (User)controller.updateUser(0, user).getBody();
		
		assertUserEquals(user, response);
	}
	
	/**
	 * Updates with a user ID different from the user ID of the user to be updated and checks for a NOT_ACCEPTABLE message.
	 */
	@Test
	public void updateWithInvalidId() {
		User user = new User("Karlo", "Karlo", "pass", 3, 3);
		user.setId(0L);
		
		ResponseEntity response = controller.updateUser(1, user);
		
		Assert.assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
	}
	
	
	/**
	 * Updates a user that does not yet exist and sends a NOT_ACCEPTABLE status message.
	 */
	@Test
	public void updateWithNonExistantUser() {
		User user = new User("Karlo", "Karlo", "pass", 3, 3);
		
		ResponseEntity responseEntity = controller.updateUser(0, user);
		
		Assert.assertEquals(HttpStatus.NOT_ACCEPTABLE, responseEntity.getStatusCode());
	}
	
	
	/**
	 * Asserts that two users are the same
	 * @param expected Expected user
	 * @param actual Actual user
	 */
	public static void assertUserEquals(User expected, User actual) {
		Assert.assertEquals(expected.getName(), actual.getName());
		Assert.assertEquals(expected.getUsername(), actual.getUsername());
		Assert.assertEquals(expected.getPassword(), actual.getPassword());
		Assert.assertEquals(expected.getPassRate(), actual.getPassRate(), ratingTolerance);
		Assert.assertEquals(expected.getDrivingRate(), actual.getDrivingRate(), ratingTolerance);
	}
}
