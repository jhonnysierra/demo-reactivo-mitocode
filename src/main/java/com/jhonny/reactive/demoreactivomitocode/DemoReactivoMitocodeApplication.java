package com.jhonny.reactive.demoreactivomitocode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pruebascurso.PruebasCurso;

@SpringBootApplication
public class DemoReactivoMitocodeApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(DemoReactivoMitocodeApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoReactivoMitocodeApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		PruebasCurso pruebasCurso = new PruebasCurso();

		pruebasCurso.combinaciones();
	}
}
