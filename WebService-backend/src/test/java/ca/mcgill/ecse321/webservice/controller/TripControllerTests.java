package ca.mcgill.ecse321.webservice.controller.TripController;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
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
import ca.mcgill.ecse321.webservice.service.TripService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class TripControllerTests {
	
	@LocalServerPort
	private int port;
	
	@Mock
	private TripService tripDAO;
	@InjectMocks
	private TripController trcontroller = new TripController();
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	
	HttpHeaders headers = new HttpHeaders();
	
	@Before
	public void setMockOutput(){
		when(tripDAO.getTrip(ArgumentMatchers.anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
			String arg = invocation.getArgument(0);
			
			switch (arg) {
			
			case "" :
				Optional<Trip> trip = Optional.of(new Trip("foo", "foo", 0, true, null, null, 0, 0, false, null));
				return trip;
			default :
				return null;
			}
		});
		
		when(tripDAO.getTrips()).thenAnswer( (InvocationOnMock invocation) -> {
			
			return null;
		});
	}

	@Test
	public void simpleTest() {
		assertTrue(true);
	}
	
	
	@Test
	public void getExistingTrip() {
		Optional<Trip> body = (Optional<Trip>)trcontroller.getTrip(0).getBody();
		Optional<Trip> resp = body;
		Optional<Trip> expected = Optional.of(new Trip("foo", "foo", 0, true, null, null, 0, 0, false, null));
		assertTripEquals(expected.get(), resp.get());
	}
	
	
	@Test
	public void getTrips() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				"http://localhost:"+port+"/api/trips",
				HttpMethod.GET, entity, String.class);

		JSONAssert.assertEquals("{startpoint:foo,endpoint:foo,distance:80,active:true,start_time:02:31:03,end_time:04:19:03,est_Trip_time:108,seats_available:4,compleated:false,vehicle:null}", response.getBody(), false);	
	}
	
	
	@Test
	public void TestAddTrip() {
		Trip trip = new Trip("Toronto", "Montreal", 0, true, null, null, 0, 0, false, null);
		trcontroller.addTrip(trip);
		assertNotNull("temp");
	}
	
	
	@Test
	public void getNonExistingTrip() {
		ResponseEntity<?> resp = trcontroller.getTrip(2);
		
		Assert.assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
	}
	
	
	@Test
	public void getInvalidTrip() {
		ResponseEntity<?> resp = trcontroller.getTrip(-1);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
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
	
	
	
	
	