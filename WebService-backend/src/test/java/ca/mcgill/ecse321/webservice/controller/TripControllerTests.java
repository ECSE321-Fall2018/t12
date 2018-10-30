package ca.mcgill.ecse321.webservice.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import ca.mcgill.ecse321.webservice.model.Trip;
import ca.mcgill.ecse321.webservice.model.User;
import ca.mcgill.ecse321.webservice.model.Vehicle;
import ca.mcgill.ecse321.webservice.service.TripService;
import ca.mcgill.ecse321.webservice.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class TripControllerTests {
	
	@LocalServerPort
	private int port;
	
	@Mock
	private TripService tripDAO;
	
	@Mock
	private UserService userDAO;
	
	@InjectMocks
	
	private TripController trcontroller = new TripController();
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	
	HttpHeaders headers = new HttpHeaders();
	
	@Before
	public void setMockOutput(){
		when(tripDAO.getTrip(ArgumentMatchers.anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
			
			Long arg = invocation.getArgument(0);
			
			if (arg == -1) {
				return null;
			} else if (arg == 0) {
				Optional<Trip> trip = Optional.of(new Trip("foo", "foo", 0, true, null, null, 0, 0, false, null, 0));				
				return trip;
			} else {
				return null;
			}
		});
		
		
	
		when(tripDAO.getTrips()).thenAnswer( (InvocationOnMock invocation) -> {
			
			return null;
		});
		
		when(userDAO.getUser(ArgumentMatchers.anyLong())).thenAnswer((InvocationOnMock invocation) ->{
			Optional<User> user = Optional.empty();
			user.get().setId(0L);
			return user;
		});
	}

	@Test
	public void simpleTest() {
		assertTrue(true);
	}
	
	
	@Test
	
	public void getExistingTrip() {		
		
		Trip resp = (Trip) trcontroller.getTrip(0).getBody();

		Optional<Trip> expected = Optional.of(new Trip("foo", "foo", 0, true, null, null, 0, 0, false, null, 0));
		assertTripEquals(expected.get(), resp);
	}
	
	@Test
	public void getNonExistingTrip() {
		ResponseEntity<?> resp = trcontroller.getTrip(2);
		
		Assert.assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
	}
	
	
	@Test
	public void getInvalidTrip() {
		ResponseEntity<?> resp = trcontroller.getTrip(-1);
		Assert.assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
	}
	
	public static void assertTripEquals(Trip expected, Trip actual) {
		Assert.assertEquals(expected.getStartpoint(), actual.getStartpoint());
		Assert.assertEquals(expected.getEndpoint(), actual.getEndpoint());
		Assert.assertEquals(expected.getDistance(), actual.getDistance());
		Assert.assertEquals(expected.isActive(), actual.isActive());
		Assert.assertEquals(expected.getStart_time(), actual.getStart_time());
		Assert.assertEquals(expected.getEnd_time(), actual.getEnd_time());
		Assert.assertEquals(expected.getEst_Trip_time(), actual.getEst_Trip_time());
		Assert.assertEquals(expected.getSeats_available(), actual.getSeats_available());
		Assert.assertEquals(expected.isCompleated(), actual.isCompleated());
		Assert.assertEquals(expected.getVehicle(), actual.getVehicle());
		
	}
}
