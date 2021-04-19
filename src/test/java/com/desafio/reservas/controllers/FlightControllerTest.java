package com.desafio.reservas.controllers;

import com.desafio.reservas.dtos.StatusDTO;
import com.desafio.reservas.exceptions.FlightException;
import com.desafio.reservas.exceptions.HotelException;
import com.desafio.reservas.fixtures.*;
import com.desafio.reservas.services.FlightService;
import com.desafio.reservas.services.FlightServiceImple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class FlightControllerTest {

    private FlightController controller;
    private FlightService serviceMock;

    @BeforeEach
    void setUp() {
        serviceMock = Mockito.mock(FlightServiceImple.class);
        controller = new FlightController(serviceMock);
    }

    @Test
    @DisplayName("List all flights")
    void listAllFlights() throws FlightException {
        Mockito.when(serviceMock.listFlightsAvailable(any()))
                .thenReturn(FlightDTOFixture.defaultFormattedFlights());
        ResponseEntity actual = controller.listFlights(new HashMap<>());
        ResponseEntity expected = new ResponseEntity(FlightDTOFixture.defaultFormattedFlights(), HttpStatus.OK);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Making a flight reservation")
    void confirmReservation() throws FlightException {
        Mockito.when(serviceMock.performReservation(any()))
                .thenReturn(ResponseFlightDTOFixture.defaultResponseDTO());
        ResponseEntity actual = controller.makeReservation(PayloadDTOFixture.defaultFlightPayloadDTO(ReservationDTOFixture.defaultFlightReservationDTO()));
        ResponseEntity expected = new ResponseEntity(ResponseFlightDTOFixture.defaultResponseDTO(), HttpStatus.OK);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Handling exceptions")
    void handleFlightException() throws FlightException {
        FlightException e = new FlightException("Test Exception");
        ResponseEntity actual = controller.handleFlightException(e);
        ResponseEntity expected = new ResponseEntity(new StatusDTO(400, "Test Exception"), HttpStatus.BAD_REQUEST);
        assertEquals(expected, actual);
    }
}