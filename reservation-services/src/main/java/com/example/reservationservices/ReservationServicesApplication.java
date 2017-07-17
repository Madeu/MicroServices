package com.example.reservationservices;

import com.example.reservationservices.endpoint.NextSequenceService;
import com.example.reservationservices.entity.Reservation;
import com.example.reservationservices.repository.MongoRepositoryConfig;
import com.example.reservationservices.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.integration.annotation.IntegrationComponentScan;

import java.util.stream.Stream;

@EnableDiscoveryClient
@IntegrationComponentScan
@EnableBinding(Sink.class)
@SpringBootApplication
public class ReservationServicesApplication {

	@Autowired
	private ReservationRepository repository;

	@Bean
	Sampler sampler(){
		return new Sampler() {
			@Override
			public boolean isSampled(Span span) {
				return true;
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ReservationServicesApplication.class, args);
	}
}
