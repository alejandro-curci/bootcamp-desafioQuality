package com.desafio.reservas.controllers;

import com.desafio.reservas.dtos.StatusDTO;
import com.desafio.reservas.exceptions.HotelException;
import com.desafio.reservas.fixtures.BookingDTOFixture;
import com.desafio.reservas.fixtures.HotelDTOFixture;
import com.desafio.reservas.fixtures.PayloadDTOFixture;
import com.desafio.reservas.fixtures.ResponseHotelDTOFixture;
import com.desafio.reservas.services.HotelService;
import com.desafio.reservas.services.HotelServiceImple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HotelControllerTest {

    private HotelController controller;
    private HotelService serviceMock;

    @BeforeEach
    void setUp() {
        serviceMock = Mockito.mock(HotelServiceImple.class);
        controller = new HotelController(serviceMock);
    }

    @Test
    @DisplayName("List all hotels")
    void listAllHotels() throws HotelException {
        Mockito.when(serviceMock.listHotelsAvailable(any())).thenReturn(HotelDTOFixture.defaultAvailableHotels());
        ResponseEntity actual = controller.listHotels(new HashMap<>());
        ResponseEntity expected = new ResponseEntity(HotelDTOFixture.defaultAvailableHotels(), HttpStatus.OK);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Booking a hotel")
    void confirmBooking() throws HotelException {
        Mockito.when(serviceMock.performBooking(any())).thenReturn(ResponseHotelDTOFixture.defaultResponseDTO());
        ResponseEntity actual = controller.bookHotel(PayloadDTOFixture.defaultHotelPayloadDTO(BookingDTOFixture.defaultBookingDTO()));
        ResponseEntity expected = new ResponseEntity(ResponseHotelDTOFixture.defaultResponseDTO(), HttpStatus.OK);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Handling exceptions")
    void handleHotelException() throws HotelException {
        HotelException e = new HotelException("Test Exception");
        ResponseEntity actual = controller.handleHotelException(e);
        ResponseEntity expected = new ResponseEntity(new StatusDTO(400, "Test Exception"), HttpStatus.BAD_REQUEST);
        assertEquals(expected, actual);
    }


}