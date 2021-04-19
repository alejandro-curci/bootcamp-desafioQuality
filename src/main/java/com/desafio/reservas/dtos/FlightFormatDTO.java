package com.desafio.reservas.dtos;

import lombok.Data;

@Data
public class FlightFormatDTO {
    private String flightNumber;
    private String origin;
    private String destination;
    private String seatType;
    private double seatPrice;
    private String dateFrom;
    private String dateTo;
}
