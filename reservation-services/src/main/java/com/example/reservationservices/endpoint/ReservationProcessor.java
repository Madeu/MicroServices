package com.example.reservationservices.endpoint;

import com.example.reservationservices.entity.Reservation;
import com.example.reservationservices.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

@Service
public class ReservationProcessor {

	@Autowired
	private final ReservationRepository reservationRepository;

	@Autowired
	private NextSequenceService sequence;

	public ReservationProcessor(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	@StreamListener(Sink.INPUT)
	public void acceptNewReservation(String reservationName) {
		this.reservationRepository.save(new Reservation(sequence.genNextSequence("reservation"), reservationName));
	}
}
