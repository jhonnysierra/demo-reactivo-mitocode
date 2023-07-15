package com.jhonny.reactive.demoreactivomitocode;

import com.jhonny.reactive.demoreactivomitocode.pruebascurso.PruebasCurso;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoReactivoMitocodeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoReactivoMitocodeApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {
		PruebasCurso pruebasCurso = new PruebasCurso();

		pruebasCurso.combinaciones();

		pruebasCurso.convertirFluxMonoFlux();

		pruebasCurso.opeTransformacion();
	}
}
