package com.gavs.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Calculator")
public class CalculatorService {
	@RequestMapping(value="/add",method= RequestMethod.GET)
	public int add(int x, int y) {
		return x+y;
	}

}
