package com.main.service;

import com.main.model.Teachers;
import java.time.LocalDate;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TeachersService {

  Mono<Teachers> createTeacher(Teachers teachers);

  Mono<Void> deleteById(String id);

  Flux<Teachers> findByBirthdateBetween(LocalDate from, LocalDate to);

  Mono<Teachers> findByDocument(String document);

  Mono<Teachers> findByFullname(String fullname);

  Mono<Teachers> findbyId(String id);

  Flux<Teachers> getAll();

  Mono<Teachers> modifyTecacher(String id, Teachers teachers);
}
