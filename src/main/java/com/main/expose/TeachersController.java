package com.main.expose;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.main.model.Teachers;
import com.main.service.TeachersService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("API/Teachers")
public class TeachersController {

  @Autowired private TeachersService service;

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Teachers> createTeacher(@RequestBody Teachers teachers) {
    return service.createTeacher(teachers);
  }

  @DeleteMapping("/delete/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> deleteTeacher(@PathVariable String id) {
    return service.deleteById(id);
  }

  @GetMapping("/document/{number}")
  public Mono<Teachers> findbyDocument(@PathVariable String number) {
    return service.findByDocument(number);
  }

  @GetMapping("/fullname/{fullname}")
  public Mono<Teachers> findbyFullname(@PathVariable String fullname) {
    return service.findByFullname(fullname);
  }

  @GetMapping
  public Flux<Teachers> getall() {
    return service.getAll();
  }

  @GetMapping("/daterange/{from}/{to}")
  public Flux<Teachers> searchbyrrangeBirthdate(
      @PathVariable @DateTimeFormat(iso = ISO.DATE) LocalDate from,
      @PathVariable @DateTimeFormat(iso = ISO.DATE) LocalDate to) {
    return service.findByBirthdateBetween(from, to);
  }

  @PutMapping("/update/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Teachers> updateTeacher(@PathVariable String id, @RequestBody Teachers teachers) {
    return service.modifyTecacher(id, teachers);
  }
}
