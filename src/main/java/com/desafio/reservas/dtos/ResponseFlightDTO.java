package com.desafio.reservas.dtos;

import lombok.Data;

@Data
public class ResponseFlightDTO {
    private String userName;
    private double amount;
    private double interest;
    private double total;
    private FlightReservationResponseDTO flightReservation;
    private StatusDTO statusCode;
}
