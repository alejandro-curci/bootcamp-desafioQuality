package com.desafio.reservas.fixtures;

import com.desafio.reservas.dtos.ResponseHotelDTO;
import com.desafio.reservas.dtos.StatusDTO;

public class ResponseHotelDTOFixture {

    public static ResponseHotelDTO defaultResponseDTO() {
        ResponseHotelDTO response = new ResponseHotelDTO();
        response.setUserName("pepitogomez@gmail.com");
        response.setAmount(54350);
        response.setInterest(10);
        response.setTotal(59785);
        response.setBooking(BookingDTOFixture.defaultBookingResponseDTO());
        response.setStatusCode(new StatusDTO(200, "El proceso termino satisfactoriamente"));
        return response;
    }
}
