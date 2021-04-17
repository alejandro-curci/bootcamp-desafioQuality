package com.desafio.reservas.dtos;

import lombok.Data;

import java.util.List;

@Data
public class FlightReservationDTO {
    private String dateFrom;
    private String dateTo;
    private String origin;
    private String destination;
    private String flightNumber;
    private int seats;
    private String seatType;
    private List<UserDTO> people;
    private PaymentDTO paymentMethod;
}
