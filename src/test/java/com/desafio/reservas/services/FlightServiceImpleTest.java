package com.desafio.reservas.services;

import com.desafio.reservas.dtos.*;
import com.desafio.reservas.exceptions.FlightException;
import com.desafio.reservas.fixtures.*;
import com.desafio.reservas.repositories.FlightRepository;
import com.desafio.reservas.repositories.FlightRespositoryImple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class FlightServiceImpleTest {

    private FlightServiceImple service;
    private FlightRepository repositoryMock;

    @BeforeEach
    void setUp() {
        repositoryMock = Mockito.mock(FlightRespositoryImple.class);
        service = new FlightServiceImple(repositoryMock);
    }

    @Test
    @DisplayName("List all available flights")
    void listAllFlightsAvailable() throws FlightException {
        Mockito.when(repositoryMock.loadFlights(any())).thenReturn(FlightDTOFixture.defaultFlights());
        List<FlightFormatDTO> actual = service.listFlightsAvailable(new HashMap<>());
        List<FlightFormatDTO> expected = FlightDTOFixture.defaultFormattedFlights();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Parameters validation (1/7)")
    void validateParameters1() {
        Mockito.when(repositoryMock.loadFlights(any())).thenReturn(FlightDTOFixture.defaultFlights());
        FlightException e = assertThrows(FlightException.class,
                () -> service.validateParameters("15-12-2020", "", "", ""));
        assertEquals("DateFrom is not valid", e.getMessage());
    }

    @Test
    @DisplayName("Parameters validation (2/7)")
    void validateParameters2() {
        Mockito.when(repositoryMock.loadFlights(any())).thenReturn(FlightDTOFixture.defaultFlights());
        FlightException e = assertThrows(FlightException.class,
                () -> service.validateParameters("", "18/Jul/2020", "", ""));
        assertEquals("DateTo is not valid", e.getMessage());
    }

    @Test
    @DisplayName("Parameters validation (3/7)")
    void validateParameters3() {
        Mockito.when(repositoryMock.loadFlights(any())).thenReturn(FlightDTOFixture.defaultFlights());
        FlightException e = assertThrows(FlightException.class,
                () -> service.validateParameters("03/09/2020", "18/07/2020", "", ""));
        assertEquals("Invalid dates. DateFrom must be before than DateTo", e.getMessage());
    }

    @Test
    @DisplayName("Parameters validation (4/7)")
    void validateParameters4() {
        Mockito.when(repositoryMock.loadFlights(any())).thenReturn(FlightDTOFixture.defaultFlights());
        FlightException e = assertThrows(FlightException.class,
                () -> service.validateParameters("03/09/2024", "", "", ""));
        assertEquals("No results around Date From = 03/09/2024", e.getMessage());
    }

    @Test
    @DisplayName("Parameters validation (5/7)")
    void validateParameters5() {
        Mockito.when(repositoryMock.loadFlights(any())).thenReturn(FlightDTOFixture.defaultFlights());
        FlightException e = assertThrows(FlightException.class,
                () -> service.validateParameters("", "03/09/2018", "", ""));
        assertEquals("No results around Date To = 03/09/2018", e.getMessage());
    }

    @Test
    @DisplayName("Parameters validation (6/7)")
    void validateParameters6() {
        Mockito.when(repositoryMock.loadFlights(any())).thenReturn(FlightDTOFixture.defaultFlights());
        FlightException e = assertThrows(FlightException.class,
                () -> service.validateParameters("", "", "Miami", ""));
        assertEquals("Origin does not exist", e.getMessage());
    }

    @Test
    @DisplayName("Parameters validation (7/7)")
    void validateParameters7() {
        Mockito.when(repositoryMock.loadFlights(any())).thenReturn(FlightDTOFixture.defaultFlights());
        FlightException e = assertThrows(FlightException.class,
                () -> service.validateParameters("", "", "", "Berlín"));
        assertEquals("Destination does not exist", e.getMessage());
    }

    @Test
    @DisplayName("Perform reservation succesfully")
    void performReservationSuccessfully() throws FlightException {
        Mockito.when(repositoryMock.loadFlights(any())).thenReturn(FlightDTOFixture.defaultFlights());
        ResponseFlightDTO actual = service.performReservation(PayloadDTOFixture.defaultFlightPayloadDTO(ReservationDTOFixture.defaultFlightReservationDTO()));
        ResponseFlightDTO expected = ResponseFlightDTOFixture.defaultResponseDTO();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Reservation verification (1/10)")
    void verifyBooking1() {
        Mockito.when(repositoryMock.loadFlights(any())).thenReturn(FlightDTOFixture.defaultFlights());
        FlightException e = assertThrows(FlightException.class,
                () -> service.verifyReservation(PayloadDTOFixture.defaultFlightPayloadDTO(ReservationDTOFixture.defaultFlightReservationWrongFlightNumber())));
        assertEquals("The flight number is not valid", e.getMessage());
    }

    @Test
    @DisplayName("Reservation verification (2/10)")
    void verifyBooking2() {
        Mockito.when(repositoryMock.loadFlights(any())).thenReturn(FlightDTOFixture.defaultFlights());
        FlightException e = assertThrows(FlightException.class,
                () -> service.verifyReservation(PayloadDTOFixture.defaultFlightPayloadDTO(ReservationDTOFixture.defaultFlightReservationInvalidDates())));
        assertEquals("Dates are not valid.", e.getMessage());
    }

    @Test
    @DisplayName("Reservation verification (3/10)")
    void verifyBooking3() {
        Mockito.when(repositoryMock.loadFlights(any())).thenReturn(FlightDTOFixture.defaultFlights());
        FlightException e = assertThrows(FlightException.class,
                () -> service.verifyReservation(PayloadDTOFixture.defaultFlightPayloadDTO(ReservationDTOFixture.defaultFlightReservationInvalidDateFrom())));
        assertEquals("DateFrom is not correct", e.getMessage());
    }

    @Test
    @DisplayName("Reservation verification (4/10)")
    void verifyBooking4() {
        Mockito.when(repositoryMock.loadFlights(any())).thenReturn(FlightDTOFixture.defaultFlights());
        FlightException e = assertThrows(FlightException.class,
                () -> service.verifyReservation(PayloadDTOFixture.defaultFlightPayloadDTO(ReservationDTOFixture.defaultFlightReservationInvalidDateTo())));
        assertEquals("DateTo is not correct", e.getMessage());
    }

    @Test
    @DisplayName("Reservation verification (5/10)")
    void verifyBooking5() {
        Mockito.when(repositoryMock.loadFlights(any())).thenReturn(FlightDTOFixture.defaultFlights());
        FlightException e = assertThrows(FlightException.class,
                () -> service.verifyReservation(PayloadDTOFixture.defaultFlightPayloadDTO(ReservationDTOFixture.defaultFlightReservationWrongOrigin())));
        assertEquals("The origin is not correct", e.getMessage());
    }

    @Test
    @DisplayName("Reservation verification (6/10)")
    void verifyBooking6() {
        Mockito.when(repositoryMock.loadFlights(any())).thenReturn(FlightDTOFixture.defaultFlights());
        FlightException e = assertThrows(FlightException.class,
                () -> service.verifyReservation(PayloadDTOFixture.defaultFlightPayloadDTO(ReservationDTOFixture.defaultFlightReservationWrongDestination())));
        assertEquals("The destination is not correct", e.getMessage());
    }

    @Test
    @DisplayName("Reservation verification (7/10)")
    void verifyBooking7() {
        Mockito.when(repositoryMock.loadFlights(any())).thenReturn(FlightDTOFixture.defaultFlights());
        FlightException e = assertThrows(FlightException.class,
                () -> service.verifyReservation(PayloadDTOFixture.defaultFlightPayloadDTO(ReservationDTOFixture.defaultFlightReservationTooManyPeople())));
        assertEquals("Too many people on the same ticket", e.getMessage());
    }

    @Test
    @DisplayName("Reservation verification (8/10)")
    void verifyBooking8() {
        Mockito.when(repositoryMock.loadFlights(any())).thenReturn(FlightDTOFixture.defaultFlights());
        FlightException e = assertThrows(FlightException.class,
                () -> service.verifyReservation(PayloadDTOFixture.defaultFlightPayloadDTO(ReservationDTOFixture.defaultFlightReservationWrongSeatType())));
        assertEquals("The seat type is not correct", e.getMessage());
    }

    @Test
    @DisplayName("Reservation verification (9/10)")
    void verifyBooking9() {
        Mockito.when(repositoryMock.loadFlights(any())).thenReturn(FlightDTOFixture.defaultFlights());
        FlightException e = assertThrows(FlightException.class,
                () -> service.verifyReservation(PayloadDTOFixture.defaultFlightPayloadWrongEmail(ReservationDTOFixture.defaultFlightReservationDTO())));
        assertEquals("The email is not valid", e.getMessage());
    }

    @Test
    @DisplayName("Reservation verification (10/10)")
    void verifyBooking10() {
        Mockito.when(repositoryMock.loadFlights(any())).thenReturn(FlightDTOFixture.defaultFlights());
        FlightException e = assertThrows(FlightException.class,
                () -> service.verifyReservation(PayloadDTOFixture.defaultFlightPayloadDTO(ReservationDTOFixture.defaultFlightReservationDebitPayment())));
        assertEquals("You cannot pay in many dues with DEBIT", e.getMessage());
    }
}