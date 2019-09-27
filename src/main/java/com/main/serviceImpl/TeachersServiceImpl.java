package com.main.serviceImpl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import com.main.model.Teachers;
import com.main.repository.TeachersRepository;
import com.main.service.TeachersService;
import org.springframework.stereotype.Service;



import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TeachersServiceImpl implements TeachersService {
  @Autowired private TeachersRepository repository;

  @Override
  public Mono<Teachers> createTeacher(Teachers teachers) {
    return repository.save(teachers);
  }

  @Override
  public Mono<Void> deleteById(String id) {
    return repository.deleteById(id);
  }

  @Override
  public Flux<Teachers> findByBirthdateBetween(LocalDate from, LocalDate to) {
    return repository.findByBirthdateBetween(from, to);
  }

  @Override
  public Mono<Teachers> findByDocument(String document) {
    return repository.findByDocument(document);
  }

  @Override
  public Mono<Teachers> findByFullname(String fullname) {
    return repository.findByFullname(fullname);
  }

  @Override
  public Mono<Teachers> findbyId(String id) {
    return repository.findById(id);
  }

  @Override
  public Flux<Teachers> getAll() {
    return repository.findAll();
  }

  @Override
  public Mono<Teachers> modifyTecacher(String id, Teachers teachers) {
    teachers.setId(id);
    return repository.save(teachers);
  }
}
