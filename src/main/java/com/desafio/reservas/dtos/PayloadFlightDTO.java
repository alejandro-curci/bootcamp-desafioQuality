package com.desafio.reservas.dtos;

import lombok.Data;

@Data
public class PayloadFlightDTO {
    private String userName;
    private FlightReservationDTO flightReservation;
}
