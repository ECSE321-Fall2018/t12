package ca.mcgill.ecse321.webservice.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainControllerTests {
	
	@InjectMocks
	private MainController mainController = new MainController();
	
	@Test
	public void greetingTest() {
		Assert.assertEquals("Hello, world!", mainController.greeting());
	}
}
