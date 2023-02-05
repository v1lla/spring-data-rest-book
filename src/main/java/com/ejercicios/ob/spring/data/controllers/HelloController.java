package com.ejercicios.ob.spring.data.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@Value("${app.saludo.mensaje}")
	String mensajeSaludoEntorno;
	
	@GetMapping("/saludo")
	public String saludo() {
		return mensajeSaludoEntorno;
	}
}
