package com.main.service;

import java.time.LocalDate;


import com.main.model.Teachers;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TeachersService {

	Mono<Teachers> findByFullname(String fullname);

	Mono <Teachers> findByDocument(String document);

	Flux<Teachers> findByBirthdateBetween(LocalDate from, LocalDate to);
	
	Mono<Teachers> createTeacher( Teachers teachers);

	Mono<Teachers> modifyTecacher(String id, Teachers teachers);

	Mono<Void> deleteById(String id);

	Mono<Teachers> findbyId(String id);
	
	Flux<Teachers> getAll();
}
