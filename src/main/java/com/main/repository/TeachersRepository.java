package com.main.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.main.model.Teachers;

public interface TeachersRepository extends ReactiveMongoRepository<Teachers, String> {

}
