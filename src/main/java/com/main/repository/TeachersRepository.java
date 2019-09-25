package com.main.repository;

import java.time.LocalDate;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.main.model.Teachers;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TeachersRepository extends ReactiveMongoRepository<Teachers, String> {

	Mono<Teachers> findByFullname(String fullname);

	Mono <Teachers> findByDocument(String document);

	Flux<Teachers> findByBirthdateBetween(LocalDate from, LocalDate to);
}
