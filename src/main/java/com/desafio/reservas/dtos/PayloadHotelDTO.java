package com.desafio.reservas.dtos;

import lombok.Data;

@Data
public class PayloadHotelDTO {
    private String userName;
    private BookingDTO booking;
}
