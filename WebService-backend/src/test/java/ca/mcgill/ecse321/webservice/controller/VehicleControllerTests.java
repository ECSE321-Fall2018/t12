package ca.mcgill.ecse321.webservice.controller;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.awt.List;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;

import javax.xml.ws.Response;

import org.assertj.core.internal.Iterables;
import org.hibernate.mapping.Set;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.webservice.model.User;
import ca.mcgill.ecse321.webservice.model.Vehicle;
import ca.mcgill.ecse321.webservice.service.UserService;
import ca.mcgill.ecse321.webservice.service.VehicleService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleControllerTests {
	
	@Mock
	private VehicleService vehicleDAO;
	@Mock
	private UserService userDAO;
	@InjectMocks
	private VehicleController controller = new VehicleController();
	
	private int updateCountId = 0;
	private int addCountId = 0;
	
	
	@Before
	public void setMockOutput() {
		when(vehicleDAO.getVehicle(ArgumentMatchers.anyLong())).then((InvocationOnMock invocation)->{
			Long arg = invocation.getArgument(0);
			
			
			if(arg == -1){
				return null;
			} else if (arg == 0) {
				User user = new User("karl", "karl", "pass", 3, 3);
				Optional<Vehicle> vehicle = Optional.of(new Vehicle("Camry","Toyota", "Blue", user));
				vehicle.get().setId(0L);
				return vehicle;
			} else {
				return null;
			}
		});
		
		when(vehicleDAO.addVehicle(ArgumentMatchers.any())).then((InvocationOnMock invocation)->{
			Vehicle arg = invocation.getArgument(0);
			
			if(arg == null) {
				return null;
			}
			
			if(arg.getId() == null) {
				arg.setId(new Long(addCountId++));
			}
			
			return arg;
		});
		
		when(vehicleDAO.updateVehicle(ArgumentMatchers.any())).then((InvocationOnMock invocation)->{
			Vehicle arg = invocation.getArgument(0);
			
			if(arg == null) {
				return null;
			}
			
			if(arg.getId() == null) {
				arg.setId(new Long(updateCountId++));
			}
			
			return arg;		
		});
		
		when(userDAO.getUser(ArgumentMatchers.anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
			Long arg = invocation.getArgument(0);
			
			
			if(arg == -1){
				return null;
			} else if (arg == 0) {
				Optional<User> user = Optional.of(new User("Karlo", "Karlo", "pass", 3, 3));
				user.get().setId(0L);
				user.get().setVehicles(new HashSet<Vehicle>(Arrays.asList(new Vehicle("Camry", "Toyota", "Blue", user.get()))));
				return user;
			} else {
				return null;
			}
		  });
	}
	
	//getVehicle tests
	
	@Test
	public void getExistingVehicle() {
		Vehicle resp = (Vehicle) controller.getVehicleById(0).getBody();
		
		User user = new User("karl", "karl", "pass", 3, 3);
		Vehicle expected = new Vehicle("Camry","Toyota", "Blue", user);
		expected.setId(0L);
		
		assertNotNull(resp.getUser());
		assertVehicleEquals(expected, resp);
	}
	
	@Test
	public void getExistingVehiclesFromUser() {
		Iterable<Vehicle> vehicles = (Iterable<Vehicle>) controller.getVehiclesByUserId(0).getBody();
		ArrayList<Vehicle> arrayList = new ArrayList<Vehicle>();
		vehicles.forEach(arrayList::add);
		
		User user = new User("Karlo", "Karlo", "pass", 3, 3);
		user.setId(0L);
		Vehicle vehicle1 = new Vehicle("Camry", "Toyota", "Blue", user);
		
		for (Vehicle vehicle : arrayList) {
			assertVehicleEquals(vehicle1, vehicle);
		}
	}
	
	@Test
	public void getNonExistingVehicle() {
		ResponseEntity<?> resp = controller.getVehicleById(3);
		
		Assert.assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
	}
	
	@Test
	public void getInvalidVehicle() {
		ResponseEntity<?> resp = controller.getVehicleById(-1);
		
		Assert.assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
	
	}
	
	
	
	//addVehicle tests
	
	@Test
	public void addValidVehicle() {
		User user = new User("Karlo", "Karlo", "pass", 3, 3);
		user.setId(0L);
		
		Vehicle expected = new Vehicle("Camry","Toyota", "Blue", user);
		user.setId(0L);
		
		Vehicle response = (Vehicle) controller.addVehicle(0, expected).getBody();
		
		assertVehicleEquals(expected, response);
	}
	
	
	//updateVehicle
	
	@Test
	public void updateWithValidVehicle() {
		User user = new User("Karlo", "Karlo", "pass", 3, 3);
		
		Vehicle expected = new Vehicle("Camry","Toyota", "Blue", user);
		
		Vehicle response = (Vehicle) controller.updateVehicle(0, expected).getBody();
		
		assertVehicleEquals(expected, response);
	}
	
	/**
	 * Asserts that two vehicles are the same
	 * @param expected
	 * @param actual
	 */
	public static void assertVehicleEquals(Vehicle expected, Vehicle actual) {
		Assert.assertEquals(expected.getId(), actual.getId());
		Assert.assertEquals(expected.getColor(), actual.getColor());
		Assert.assertEquals(expected.getMake(), actual.getMake());
		Assert.assertEquals(expected.getModel(), actual.getModel());
		if(expected.getUser() != null && actual.getUser() != null) {
			Assert.assertEquals(expected.getUser().getId(), actual.getUser().getId());
		} else if (expected.getUser() == null && actual.getUser() == null) {
			Assert.assertTrue("Both users are null", true);
		} else {
			fail("One user is null");
		}
	}
}
