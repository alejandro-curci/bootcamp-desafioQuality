package com.desafio.reservas.dtos;

import lombok.Data;

import java.util.List;

@Data
public class BookingDTO {
    private String dateFrom;
    private String dateTo;
    private String destination;
    private String hotelCode;
    private int peopleAmount;
    private String roomType;
    private List<UserDTO> people;
    private PaymentDTO paymentMethod;
}
