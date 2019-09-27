package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
public class Proy2MicroserviceTeachersApplication {

  public static void main(String[] args) {
    SpringApplication.run(Proy2MicroserviceTeachersApplication.class, args);
  }
}
