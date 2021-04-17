package com.desafio.reservas.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HotelDTO {
    private String hotelCode;
    private String name;
    private String city;
    private String roomType;
    private double price;
    private LocalDate availableFrom;
    private LocalDate availableTo;
    private boolean reserved;
}
