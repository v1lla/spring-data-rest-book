package com.ejercicios.ob.spring.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.ejercicios.ob.spring.data.entities.Laptop;
import com.ejercicios.ob.spring.data.repositories.LaptopRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext contexto = SpringApplication.run(Application.class, args);
		LaptopRepository laptopRepository = contexto.getBean(LaptopRepository.class);

		Laptop laptop1 = new Laptop(1L, "HP", "i5", 4);
		Laptop laptop2 = new Laptop(2L, "ACER", "i7", 5);

		laptopRepository.save(laptop1);
		laptopRepository.save(laptop2);

	}

}
