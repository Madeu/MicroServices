package com.example.reservationclient.api;

import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
interface ReservationWriter {

	@Gateway(requestChannel = Source.OUTPUT)
	void write(String reservationName);
}