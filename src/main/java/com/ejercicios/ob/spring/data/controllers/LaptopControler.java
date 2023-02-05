package com.ejercicios.ob.spring.data.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ejercicios.ob.spring.data.entities.Laptop;
import com.ejercicios.ob.spring.data.repositories.LaptopRepository;

@RestController
public class LaptopControler {

	private LaptopRepository laptopRepository;

	public LaptopControler(LaptopRepository laptopRepository) {
		this.laptopRepository = laptopRepository;
	}

	@GetMapping("/api/laptops")
	public ResponseEntity<List<Laptop>> findAll() {
		return ResponseEntity.ok(laptopRepository.findAll());
	}

	@GetMapping("/api/laptops/{id}")
	public ResponseEntity<Laptop> findOneById(@PathVariable Long id) {
		Optional<Laptop> laptopOpt = laptopRepository.findById(id);
		if (laptopOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(laptopOpt.get());
	}

	@PostMapping("/api/laptops")
	public ResponseEntity<Laptop> create(@RequestBody Laptop laptop) {

		if (laptop.getId() != null) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(laptopRepository.save(laptop));
	}

	@PutMapping("/api/laptops")
	public ResponseEntity<Laptop> update(@RequestBody Laptop laptop) {
		if (laptop.getId() == null) {
			return ResponseEntity.badRequest().build();
		}

		if (!laptopRepository.existsById(laptop.getId())) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(laptopRepository.save(laptop));
	}

	@DeleteMapping("/api/laptops/{id}")
	public ResponseEntity<Laptop> delete(@PathVariable Long id) {
		if (!laptopRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		laptopRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/api/laptops")
	public ResponseEntity<Laptop> deleteAll() {
		if (laptopRepository.count() == 0) {
			return ResponseEntity.noContent().build();
		}

		laptopRepository.deleteAll();
		return ResponseEntity.ok().build();
	}
}
