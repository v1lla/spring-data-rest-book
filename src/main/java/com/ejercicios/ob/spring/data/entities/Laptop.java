package com.ejercicios.ob.spring.data.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Laptop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String marca;
	private String procesador;
	private Integer numeroPuertos;

	public Laptop() {
		super();
	}

	public Laptop(Long id, String marca, String procesador, Integer numeroPuertos) {
		super();
		this.id = id;
		this.marca = marca;
		this.procesador = procesador;
		this.numeroPuertos = numeroPuertos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getProcesador() {
		return procesador;
	}

	public void setProcesador(String procesador) {
		this.procesador = procesador;
	}

	public Integer getNumeroPuertos() {
		return numeroPuertos;
	}

	public void setNumeroPuertos(Integer numeroPuertos) {
		this.numeroPuertos = numeroPuertos;
	}

	@Override
	public String toString() {
		return "Laptop [id=" + id + ", marca=" + marca + ", procesador=" + procesador + ", numeroPuertos="
				+ numeroPuertos + "]";
	}

}
