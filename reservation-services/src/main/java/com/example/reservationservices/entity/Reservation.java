package com.example.reservationservices.entity;


import org.springframework.data.annotation.Id;

public class Reservation {

	@Id
	private Long id;
	private String reservationName;

	public Reservation(Long id, String reservationName) {
		this.id = id;
		this.reservationName = reservationName;
	}

	public Reservation() {
	}

	@Override
	public String toString() {
		return "Reservation{" +
			"id=" + id +
			", reservationName='" + reservationName + '\'' +
			'}';
	}

	public Long getId() {
		return id;
	}

	public String getReservationName() {
		return reservationName;
	}
}
