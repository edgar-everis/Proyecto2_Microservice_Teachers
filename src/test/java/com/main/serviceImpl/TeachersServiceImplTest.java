package com.main.serviceImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.springframework.test.context.junit4.SpringRunner;

import com.main.model.Teachers;
import com.main.repository.TeachersRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.reactivestreams.Publisher;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TeachersServiceImplTest {

  @Mock private TeachersRepository Repository;

  @InjectMocks private TeachersServiceImpl Service;

  @Test
  public void testCreateTeacher() {
    LocalDate date = LocalDate.parse("2018-05-05");
    Teachers teacher = new Teachers();
    teacher.setId("1");
    teacher.setBirthdate(date);
    teacher.setGender("M");
    teacher.setFullname("Carlos");
    teacher.setTypedoc("dni");
    teacher.setDocument("666666");
    when(Repository.save(teacher)).thenReturn(Mono.just(teacher));
    Mono<Teachers> actual = Service.createTeacher(teacher);
    assertResults(actual, teacher);
  }

  @Test
  public void testDeleteById() {
    LocalDate date = LocalDate.parse("2018-05-05");
    Teachers teacher = new Teachers();
    teacher.setId("2");
    teacher.setFullname("Ana");
    teacher.setBirthdate(date);
    teacher.setGender("M");
    teacher.setTypedoc("dni");
    teacher.setDocument("777777");
    when(Repository.delete(teacher)).thenReturn(Mono.empty());
  }

  @Test
  public void testFindByBirthdateBetween() {
    Teachers teach = new Teachers();
    teach.setId("1");
    teach.setFullname("Pedro");
    teach.setBirthdate(LocalDate.of(1996, 05, 05));
    teach.setGender("M");
    teach.setTypedoc("dni");
    teach.setDocument("999999");
    when(Repository.findByBirthdateBetween(LocalDate.of(1991, 05, 05), LocalDate.of(2000, 05, 02)))
        .thenReturn(Flux.just(teach));
    Flux<Teachers> actual =
        Service.findByBirthdateBetween(LocalDate.of(1991, 05, 05), LocalDate.of(2000, 05, 02));
    assertResults(actual, teach);
  }

  @Test
  public void testFindByDocument() {
    LocalDate date = LocalDate.parse("2018-05-05");
    Teachers teacher = new Teachers();
    teacher.setId("1");
    teacher.setFullname("Andres");
    teacher.setGender("M");
    teacher.setBirthdate(date);
    teacher.setDocument("111111");
    teacher.setTypedoc("dni");

    when(Repository.findByDocument("111111")).thenReturn(Mono.just(teacher));
    Mono<Teachers> actual = Service.findByDocument("111111");
    assertResults(actual, teacher);
  }

  @Test
  public void testFindByFullname() {
    LocalDate date = LocalDate.parse("2018-05-05");
    Teachers teacher = new Teachers();
    teacher.setId("3");
    teacher.setFullname("Andres");
    teacher.setBirthdate(date);
    teacher.setGender("M");
    teacher.setDocument("888888");
    teacher.setTypedoc("dni");

    when(Repository.findByFullname("Andres")).thenReturn(Mono.just(teacher));
    Mono<Teachers> actual = Service.findByFullname("Andres");
    assertResults(actual, teacher);
    System.out.println(actual);
    System.out.println(teacher.getFullname());
  }

  @Test
  public void testFindbyId() {
    LocalDate date = LocalDate.parse("2018-05-05");
    Teachers teacher = new Teachers();
    teacher.setId("4");
    teacher.setFullname("Andres");
    teacher.setBirthdate(date);
    teacher.setGender("M");
    teacher.setDocument("888888");
    teacher.setTypedoc("dni");

    when(Repository.findById("4")).thenReturn(Mono.just(teacher));
    Mono<Teachers> actual = Service.findbyId("4");
    assertResults(actual, teacher);
    System.out.println(actual);
    System.out.println(teacher.getFullname());
  }

  @Test
  public void testGetAll() {
    LocalDate date = LocalDate.parse("2018-05-05");
    Teachers teacher = new Teachers();
    teacher.setId("1");
    teacher.setFullname("Juan");
    teacher.setBirthdate(date);
    teacher.setTypedoc("dni");
    teacher.setDocument("123456");
    teacher.setGender("M");

    when(Service.getAll()).thenReturn(Flux.just(teacher));
    Flux<Teachers> actual = Service.getAll();
    assertResults(actual, teacher);
  }

  @Test
  public void testModifyTecacher() {}

  private void assertResults(Publisher<Teachers> publisher, Teachers... expectedProducts) {
    StepVerifier.create(publisher).expectNext(expectedProducts).verifyComplete();
  }
}
