package com.example.reservationservices.repository;

import com.example.reservationservices.entity.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReservationRepository extends MongoRepository<Reservation, Long> {

	List<Reservation> findByReservationName(String rn );
}
