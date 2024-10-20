package org.amal.taxiauthservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TaxiAuthServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(TaxiAuthServiceApplication.class, args);
  }

}
