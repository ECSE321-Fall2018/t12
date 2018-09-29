package ca.mcgill.ecse321.webservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	@RequestMapping("/")
	public String greeting()
	{
		return "Hello, world!";
	}
	
}
