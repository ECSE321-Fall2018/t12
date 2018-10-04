package ca.mcgill.ecse321.webservice.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
	
	@Before
	public void setMockOutput(){
		when(userDAO.getUser(ArgumentMatchers.anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
			User user = new User("Karlo", "Karlo", "pass", 3, 3, null, null);
		    return user;
			
		  });
	}
	
	@Test
	public void simpleTest() {
		assertTrue(true);
	}
	
	@Test
	public void getUsers() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				"http://localhost:"+port+"/api/users",
				HttpMethod.GET, entity, String.class);

		
		
		JSONAssert.assertEquals("id:0,name:Karl,username:Karlo,password:pass,drivingRate:3,passRate:3,registrations:null,vehicles:null", response.getBody(), false);
		fail();
		
	}
	
	/*@Test
	public void TestAddUser() {
		User user = new User("Karl", "karlo", "pass", 5, 5, null, null);
		controller.addUser(user);
		assertNotNull("temp");
	}*/
	
}
