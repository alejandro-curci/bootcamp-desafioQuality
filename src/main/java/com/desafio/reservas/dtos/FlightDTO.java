package com.desafio.reservas.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FlightDTO {
    private String flightNumber;
    private String origin;
    private String destination;
    private String seatType;
    private double seatPrice;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
