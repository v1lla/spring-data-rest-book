package com.ejercicios.ob.spring.data.controllers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.ejercicios.ob.spring.data.entities.Laptop;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class LaptopControlerTest {

	@LocalServerPort
	private int port;

	@Autowired
	RestTemplateBuilder restTemplateBuilder;

	TestRestTemplate testRestTemplate;

	@BeforeEach
	void beforeEach() {
		restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
		testRestTemplate = new TestRestTemplate(restTemplateBuilder);
	}

	@Test
	void findAllTest() {
		ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptops", Laptop[].class);

		assertTrue(response.getStatusCode() == HttpStatus.OK);
		assertTrue(Arrays.asList(response.getBody()).size() == 0);

	}

	@Test
	void findByIdTest() {
		ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptops/2", Laptop[].class);

		assertTrue(response.getStatusCode() == HttpStatus.NOT_FOUND);

	}

	@Test
	void createTest() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		List<MediaType> listMediaType = new ArrayList<MediaType>();
		listMediaType.add(MediaType.APPLICATION_JSON);
		httpHeaders.setAccept(listMediaType);

		// PRUEBA KO - Al crear se le indica el id
		String json = """
				{
				    "id" : 3,
				    "marca": "MarcaDummy testing",
				    "procesador": "i1",
				    "numeroPuertos": 99
				}
				""";

		HttpEntity<String> httpEntity = new HttpEntity<String>(json, httpHeaders);
		ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, httpEntity,
				Laptop.class);

		assertTrue(response.getStatusCode() == HttpStatus.BAD_REQUEST);

		// PRUEBA OK
		json = """
				{
				    "marca": "MarcaDummy testing",
				    "procesador": "i1",
				    "numeroPuertos": 99
				}
				""";

		httpEntity = new HttpEntity<String>(json, httpHeaders);
		response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, httpEntity, Laptop.class);

		assertTrue(response.getStatusCode() == HttpStatus.OK);
		assertTrue(response.getBody().getId() != null);

	}

	@Test
	void updateTest() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		List<MediaType> listMediaType = new ArrayList<MediaType>();
		listMediaType.add(MediaType.APPLICATION_JSON);
		httpHeaders.setAccept(listMediaType);

		// PRUEBA KO - se le pasa un id que no existe
		String json = """
				{
				    "id" : 1,
				    "marca": "MarcaDummy testing",
				    "procesador": "i1",
				    "numeroPuertos": 99
				}
				""";

		HttpEntity<String> httpEntity = new HttpEntity<String>(json, httpHeaders);
		ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.PUT, httpEntity,
				Laptop.class);

		assertTrue(response.getStatusCode() == HttpStatus.NOT_FOUND);

	}

	@Test
	void deleteTest() {

		testRestTemplate.delete("/api/laptops/1");
		// Habría que mockear una lista de objetos laptop para comprobar que
		// efectivamente se ha eliminado uno

	}

	@Test
	void deleteAllTest() {

		testRestTemplate.delete("/api/laptops");
		// Habría que mockear una lista de objetos laptop para comprobar que
		// efectivamente se han eliminado todos

	}

}
