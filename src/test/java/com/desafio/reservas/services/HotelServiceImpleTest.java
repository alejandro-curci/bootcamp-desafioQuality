package com.desafio.reservas.services;

import com.desafio.reservas.dtos.BookingResponseDTO;
import com.desafio.reservas.dtos.HotelFormatDTO;
import com.desafio.reservas.dtos.ResponseHotelDTO;
import com.desafio.reservas.exceptions.HotelException;
import com.desafio.reservas.fixtures.BookingDTOFixture;
import com.desafio.reservas.fixtures.HotelDTOFixture;
import com.desafio.reservas.fixtures.PayloadDTOFixture;
import com.desafio.reservas.fixtures.ResponseHotelDTOFixture;
import com.desafio.reservas.repositories.HotelRepository;
import com.desafio.reservas.repositories.HotelRepositoryImple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HotelServiceImpleTest {

    private HotelServiceImple service;
    private HotelRepository repositoryMock;

    @BeforeEach
    void setUp() {
        repositoryMock = Mockito.mock(HotelRepositoryImple.class);
        service = new HotelServiceImple(repositoryMock);
    }

    @Test
    @DisplayName("List all available hotels")
    void listAllHotelsAvailable() throws HotelException {
        Mockito.when(repositoryMock.loadHotels(any())).thenReturn(HotelDTOFixture.defaultHotels());
        List<HotelFormatDTO> actual = service.listHotelsAvailable(new HashMap<>());
        List<HotelFormatDTO> expected = HotelDTOFixture.defaultAvailableHotels();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Parameters validation (1/6)")
    void validateParameters1() {
        Mockito.when(repositoryMock.loadHotels(any())).thenReturn(HotelDTOFixture.defaultHotels());
        HotelException e = assertThrows(HotelException.class,
                () -> service.validateParameters("15-12-2020", "", ""));
        assertEquals("DateFrom is not valid", e.getMessage());
    }

    @Test
    @DisplayName("Parameters validation (2/6)")
    void validateParameters2() {
        Mockito.when(repositoryMock.loadHotels(any())).thenReturn(HotelDTOFixture.defaultHotels());
        HotelException e = assertThrows(HotelException.class,
                () -> service.validateParameters("", "18/Jul/2020", ""));
        assertEquals("DateTo is not valid", e.getMessage());
    }

    @Test
    @DisplayName("Parameters validation (3/6)")
    void validateParameters3() {
        Mockito.when(repositoryMock.loadHotels(any())).thenReturn(HotelDTOFixture.defaultHotels());
        HotelException e = assertThrows(HotelException.class,
                () -> service.validateParameters("03/09/2020", "18/07/2020", ""));
        assertEquals("Invalid dates. DateFrom must be before than DateTo", e.getMessage());
    }

    @Test
    @DisplayName("Parameters validation (4/6)")
    void validateParameters4() {
        Mockito.when(repositoryMock.loadHotels(any())).thenReturn(HotelDTOFixture.defaultHotels());
        HotelException e = assertThrows(HotelException.class,
                () -> service.validateParameters("03/09/2024", "", ""));
        assertEquals("No results for Date From = 03/09/2024", e.getMessage());
    }

    @Test
    @DisplayName("Parameters validation (5/6)")
    void validateParameters5() {
        Mockito.when(repositoryMock.loadHotels(any())).thenReturn(HotelDTOFixture.defaultHotels());
        HotelException e = assertThrows(HotelException.class,
                () -> service.validateParameters("", "03/09/2018", ""));
        assertEquals("No results for Date To = 03/09/2018", e.getMessage());
    }

    @Test
    @DisplayName("Parameters validation (6/6)")
    void validateParameters6() {
        Mockito.when(repositoryMock.loadHotels(any())).thenReturn(HotelDTOFixture.defaultHotels());
        HotelException e = assertThrows(HotelException.class,
                () -> service.validateParameters("", "", "Miami"));
        assertEquals("Destination does not exist", e.getMessage());
    }

    @Test
    @DisplayName("Perform booking succesfully")
    void performBookingSuccessfully() throws HotelException {
        Mockito.when(repositoryMock.loadHotels(any())).thenReturn(HotelDTOFixture.defaultHotels());
        ResponseHotelDTO actual = service.performBooking(PayloadDTOFixture.defaultHotelPayloadDTO(BookingDTOFixture.defaultBookingDTO()));
        ResponseHotelDTO expected = ResponseHotelDTOFixture.defaultResponseDTO();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Booking verification (1/9)")
    void verifyBooking1() {
        Mockito.when(repositoryMock.loadHotels(any())).thenReturn(HotelDTOFixture.defaultHotels());
        HotelException e = assertThrows(HotelException.class,
                () -> service.verifyBooking(PayloadDTOFixture.defaultHotelPayloadDTO(BookingDTOFixture.defaultBookingWrongCode())));
        assertEquals("The code hotel is not valid", e.getMessage());
    }

    @Test
    @DisplayName("Booking verification (2/9)")
    void verifyBooking2() {
        Mockito.when(repositoryMock.loadHotels(any())).thenReturn(HotelDTOFixture.defaultHotels());
        HotelException e = assertThrows(HotelException.class,
                () -> service.verifyBooking(PayloadDTOFixture.defaultHotelPayloadDTO(BookingDTOFixture.defaultBookingAlreadyReserved())));
        assertEquals("The hotel is already reserved", e.getMessage());
    }

    @Test
    @DisplayName("Booking verification (3/9)")
    void verifyBooking3() {
        Mockito.when(repositoryMock.loadHotels(any())).thenReturn(HotelDTOFixture.defaultHotels());
        HotelException e = assertThrows(HotelException.class,
                () -> service.verifyBooking(PayloadDTOFixture.defaultHotelPayloadDTO(BookingDTOFixture.defaultBookingInvalidDates())));
        assertEquals("Dates are not valid.", e.getMessage());
    }

    @Test
    @DisplayName("Booking verification (4/9)")
    void verifyBooking4() {
        Mockito.when(repositoryMock.loadHotels(any())).thenReturn(HotelDTOFixture.defaultHotels());
        HotelException e = assertThrows(HotelException.class,
                () -> service.verifyBooking(PayloadDTOFixture.defaultHotelPayloadDTO(BookingDTOFixture.defaultBookingTooEarly())));
        assertEquals("The hotel gets available after your DateFrom", e.getMessage());
    }

    @Test
    @DisplayName("Booking verification (5/9)")
    void verifyBooking5() {
        Mockito.when(repositoryMock.loadHotels(any())).thenReturn(HotelDTOFixture.defaultHotels());
        HotelException e = assertThrows(HotelException.class,
                () -> service.verifyBooking(PayloadDTOFixture.defaultHotelPayloadDTO(BookingDTOFixture.defaultBookingTooLate())));
        assertEquals("The hotel needs to be available before your DateTo", e.getMessage());
    }

    @Test
    @DisplayName("Booking verification (6/9)")
    void verifyBooking6() {
        Mockito.when(repositoryMock.loadHotels(any())).thenReturn(HotelDTOFixture.defaultHotels());
        HotelException e = assertThrows(HotelException.class,
                () -> service.verifyBooking(PayloadDTOFixture.defaultHotelPayloadDTO(BookingDTOFixture.defaultBookingWrongDestination())));
        assertEquals("The destination does not exist or is mispelled", e.getMessage());
    }

    @Test
    @DisplayName("Booking verification (7/9)")
    void verifyBooking7() {
        Mockito.when(repositoryMock.loadHotels(any())).thenReturn(HotelDTOFixture.defaultHotels());
        HotelException e = assertThrows(HotelException.class,
                () -> service.verifyBooking(PayloadDTOFixture.defaultHotelPayloadDTO(BookingDTOFixture.defaultBookingWrongRoom())));
        assertEquals("The amount of people does not match the type of room", e.getMessage());
    }

    @Test
    @DisplayName("Booking verification (8/9)")
    void verifyBooking8() {
        Mockito.when(repositoryMock.loadHotels(any())).thenReturn(HotelDTOFixture.defaultHotels());
        HotelException e = assertThrows(HotelException.class,
                () -> service.verifyBooking(PayloadDTOFixture.defaultHotelPayloadWrongEmail(BookingDTOFixture.defaultBookingDTO())));
        assertEquals("The email is not valid", e.getMessage());
    }

    @Test
    @DisplayName("Booking verification (9/9)")
    void verifyBooking9() {
        Mockito.when(repositoryMock.loadHotels(any())).thenReturn(HotelDTOFixture.defaultHotels());
        HotelException e = assertThrows(HotelException.class,
                () -> service.verifyBooking(PayloadDTOFixture.defaultHotelPayloadDTO(BookingDTOFixture.defaultBookingWrongPayment())));
        assertEquals("You cannot pay in many dues with DEBIT", e.getMessage());
    }

    @Test
    @DisplayName("Valid Room (1/4)")
    void successfulTypeRoom1() throws HotelException {
        boolean actual = service.validateRoom(1, "single");
        assertEquals(true, actual);
    }

    @Test
    @DisplayName("Valid Room (2/4)")
    void successfulTypeRoom2() throws HotelException {
        boolean actual = service.validateRoom(2, "doble");
        assertEquals(true, actual);
    }

    @Test
    @DisplayName("Valid Room (3/4)")
    void successfulTypeRoom3() throws HotelException {
        boolean actual = service.validateRoom(3, "triple");
        assertEquals(true, actual);
    }

    @Test
    @DisplayName("Valid Room (4/4)")
    void successfulTypeRoom4() throws HotelException {
        boolean actual = service.validateRoom(6, "múltiple");
        assertEquals(true, actual);
    }

    @Test
    @DisplayName("Invalid Type Room")
    void wrongTypeRoom() throws HotelException {
        boolean actual = service.validateRoom(3, "single");
        assertEquals(false, actual);
    }

    @Test
    @DisplayName("Too many people in the same room")
    void tooManyPeople() throws HotelException {
        assertThrows(HotelException.class, () -> service.validateRoom(17, "múltiple"));
    }

    @Test
    @DisplayName("Create object ResponseDTO")
    void createResponseDTOsuccessfully() throws HotelException {
        Mockito.when(repositoryMock.loadHotels(any())).thenReturn(HotelDTOFixture.defaultHotels());
        ResponseHotelDTO actual = service.createResponseDTO(PayloadDTOFixture.defaultHotelPayloadDTO(BookingDTOFixture.defaultBookingDTO()));
        ResponseHotelDTO expected = ResponseHotelDTOFixture.defaultResponseDTO();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Calculate amount")
    void calculateBookingAmount() {
        Mockito.when(repositoryMock.loadHotels(any())).thenReturn(HotelDTOFixture.defaultHotels());
        double actual = service.calculateAmount(BookingDTOFixture.defaultBookingDTO());
        assertEquals(54350, actual);
    }

    @Test
    @DisplayName("Create a BookingResponseDTO object")
    void createBookingResponse() {
        BookingResponseDTO actual = service.getBookingResponse(BookingDTOFixture.defaultBookingDTO());
        BookingResponseDTO expected = BookingDTOFixture.defaultBookingResponseDTO();
        assertEquals(expected, actual);
    }

}