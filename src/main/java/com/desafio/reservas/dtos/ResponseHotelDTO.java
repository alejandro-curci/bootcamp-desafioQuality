package com.desafio.reservas.dtos;

import lombok.Data;

@Data
public class ResponseHotelDTO {
    private String userName;
    private double amount;
    private double interest;
    private double total;
    private BookingResponseDTO booking;
    private StatusDTO statusCode;
}
