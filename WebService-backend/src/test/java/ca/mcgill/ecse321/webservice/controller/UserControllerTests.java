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
	}
	
	@Test
	public void simpleTest() {
		assertTrue(true);
	}
	
	@Test
	public void getUsers() throws JSONException {
		Optional<User> resp = (Optional<User>) controller.getUser(0).getBody();
		
		Optional<User> expected = Optional.of(new User("Karlo", "Karlo", "pass", 3, 3, null, null));
		
		Assert.assertEquals(resp.get().getName(), expected.get().getName());
	}	
}
