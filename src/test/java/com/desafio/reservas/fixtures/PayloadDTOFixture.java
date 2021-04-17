package com.desafio.reservas.fixtures;

import com.desafio.reservas.dtos.BookingDTO;
import com.desafio.reservas.dtos.FlightReservationDTO;
import com.desafio.reservas.dtos.PayloadFlightDTO;
import com.desafio.reservas.dtos.PayloadHotelDTO;

public class PayloadDTOFixture {

    public static PayloadFlightDTO defaultFlightPayloadWrongEmail(FlightReservationDTO reservation) {
        PayloadFlightDTO payload = new PayloadFlightDTO();
        payload.setUserName("pepitogomezgmail.com.ar");
        payload.setFlightReservation(reservation);
        return payload;
    }

    public static PayloadFlightDTO defaultFlightPayloadDTO(FlightReservationDTO reservation) {
        PayloadFlightDTO payload = new PayloadFlightDTO();
        payload.setUserName("pepitogomez@gmail.com");
        payload.setFlightReservation(reservation);
        return payload;
    }

    public static PayloadHotelDTO defaultHotelPayloadDTO(BookingDTO booking) {
        PayloadHotelDTO payload = new PayloadHotelDTO();
        payload.setUserName("pepitogomez@gmail.com");
        payload.setBooking(booking);
        return payload;
    }

    public static PayloadHotelDTO defaultHotelPayloadWrongEmail(BookingDTO booking) {
        PayloadHotelDTO payload = new PayloadHotelDTO();
        payload.setUserName("pepitogomezgmail.com.ar");
        payload.setBooking(booking);
        return payload;
    }
}
