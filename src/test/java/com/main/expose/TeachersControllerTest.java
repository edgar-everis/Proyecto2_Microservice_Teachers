package com.main.expose;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;


import com.main.model.Teachers;
import com.main.repository.TeachersRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class TeachersControllerTest {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private TeachersRepository Repository;
    private WebTestClient client;
    private List<Teachers> expectedProducts;
    
  @BeforeEach
  void setUp() throws Exception {
      
      client = WebTestClient
	      .bindToApplicationContext(applicationContext)
	      .configureClient()
	      .baseUrl("/API/Teachers")
	      .build();

	    Flux<Teachers> initData = Repository.deleteAll()
	      .thenMany(Flux.just(
	        Teachers.builder().id("1").fullname("omar").gender("male").birthdate(LocalDate.of(1993, 2, 3)).document("111111").typedoc("dni").build(),
	        Teachers.builder().id("2").fullname("jose").gender("male").birthdate(LocalDate.of(1997, 2, 3)).document("666666").typedoc("dni").build())
	        .flatMap(Repository::save))
	      .thenMany(Repository.findAll());

	    expectedProducts = initData.collectList().block();
  }

  @Test
  void testCreateTeacher() {
      Teachers expectedProduct = expectedProducts.get(0);

      client.post().uri("/create").body(Mono.just(expectedProduct), Teachers.class).exchange()
        .expectStatus().isCreated();
  }

  @Test
  void testDeleteTeacher() {
      Teachers productToDelete = expectedProducts.get(0);
      client.delete().uri("/delete/{id}", productToDelete.getId()).exchange()
        .expectStatus().isNoContent();
  }

  @Test
  void testFindbyDocument() {
      String number = "111111";
      client.get().uri("/document/{number}", number).exchange()
        .expectStatus().isOk();
  }

  @Test
  void testFindbyFullname() {
      String fullname = "jose";
      client.get().uri("/fullname/{fullname}", fullname).exchange()
        .expectStatus().isOk();
  }

  @Test
  void testGetall() {
      client.get().uri("/").exchange()
      .expectStatus().isOk();
  }

  @Test
  void testSearchbyrrangeBirthdate() {
      LocalDate from = LocalDate.of(1800,03,02);
      LocalDate to = LocalDate.of(2000,03,02);


        client.get().uri("/daterange/{from}/{to}", from,to).exchange()
          .expectStatus().isOk().
      expectBodyList(Teachers.class);
  }

  @Test
  void testUpdateTeacher() {
      Teachers expectedProduct = expectedProducts.get(0);

      client.put().uri("/update/{id}", expectedProduct.getId()).body(Mono.just(expectedProduct), Teachers.class).exchange()
        .expectStatus().isOk();
  }
}
