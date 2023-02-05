package com.ejercicios.ob.spring.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejercicios.ob.spring.data.entities.Laptop;

public interface LaptopRepository extends JpaRepository<Laptop, Long> {

}
