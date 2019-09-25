package com.main.serviceImpl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.model.Teachers;
import com.main.repository.TeachersRepository;
import com.main.service.TeachersService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TeachersServiceImpl  implements TeachersService{

	@Autowired
	private TeachersRepository repository;
	
	
	@Override
	public Mono<Teachers> findByFullname(String fullname) {
		// TODO Auto-generated method stub
		return repository.findByFullname(fullname);
	}

	@Override
	public Mono<Teachers> findByDocument(String document) {
		// TODO Auto-generated method stub
		return repository.findByDocument(document);
	}

	@Override
	public Flux<Teachers> findByBirthdateBetween(LocalDate from, LocalDate to) {
		// TODO Auto-generated method stub
		return repository.findByBirthdateBetween(from, to);
	}

	@Override
	public Mono<Teachers> createTeacher(Teachers teachers) {
		// TODO Auto-generated method stub
		return repository.save(teachers);
	}

	@Override
	public Mono<Teachers> modifyTecacher(String id, Teachers teachers) {
		// TODO Auto-generated method stub
		teachers.setId(id);
		return repository.save(teachers);
	}

	@Override
	public Mono<Void> deleteById(String id) {
		// TODO Auto-generated method stub
		return repository.deleteById(id);
	}

	@Override
	public Mono<Teachers> findbyId(String id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public Flux<Teachers> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

}
