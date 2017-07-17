package com.example.reservationclient.api;

import com.example.reservationclient.entity.Reservation;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reservations")
public class ReservationApiGateway {

	@Autowired
	private ReservationReader reservationReader;
	@Autowired
	private ReservationWriter reservationWriter;

	public Collection<String> fallback() {
		return new ArrayList<>();
	}

	@HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(method = RequestMethod.GET, value = "/names")
	public Collection<String> names(){
		return reservationReader.read()
			.getContent()
			.stream()
			.map(Reservation::getReservationName)
			.collect(Collectors.toList());
	}

	@RequestMapping(method = RequestMethod.POST)
	public void writeReservation(@RequestBody Reservation reservation) {
		this.reservationWriter.write(reservation.getReservationName());
	}
}
