package com.desafio.reservas.fixtures;

import com.desafio.reservas.dtos.ResponseFlightDTO;
import com.desafio.reservas.dtos.StatusDTO;

public class ResponseFlightDTOFixture {
    public static ResponseFlightDTO defaultResponseDTO() {
        ResponseFlightDTO response = new ResponseFlightDTO();
        response.setUserName("pepitogomez@gmail.com");
        response.setAmount(129600);
        response.setInterest(10);
        response.setTotal(142560);
        response.setFlightReservation(ReservationDTOFixture.defaultFlightReservationResponseDTO());
        response.setStatusCode(new StatusDTO(200, "El proceso termino satisfactoriamente"));
        return response;
    }
}
