package com.oracle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Entry point for the Asset Liability Management (ALM) Spring Boot application.
 *
 * <p>This class bootstraps the Spring Boot application using {@link SpringApplication}.
 * It is annotated with {@link SpringBootApplication}, which is a convenience annotation that
 * adds:
 * <ul>
 *   <li>{@code @Configuration}</li>
 *   <li>{@code @EnableAutoConfiguration}</li>
 *   <li>{@code @ComponentScan}</li>
 * </ul>
 *
 * <p>Run this application as a Java application to start the embedded server.
 *
 * @author Batch10
 * @since 1.0
 */
@SpringBootApplication()
public class AlmApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlmApplication.class, args);
	}

}

