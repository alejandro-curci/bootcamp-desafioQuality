package com.desafio.reservas.fixtures;

import com.desafio.reservas.dtos.BookingDTO;
import com.desafio.reservas.dtos.BookingResponseDTO;
import com.desafio.reservas.dtos.UserDTO;

import java.util.Arrays;
import java.util.List;

public class BookingDTOFixture {

    // every method buils a BookingDTO object to use in a particular test

    public static BookingDTO defaultBookingWrongPayment() {
        BookingDTO booking = new BookingDTO();
        booking.setHotelCode("HB-0001");
        booking.setDateFrom("10/02/2021");
        booking.setDateTo("20/02/2021");
        booking.setDestination("Buenos Aires");
        booking.setPeopleAmount(1);
        booking.setRoomType("SINGLE");
        List<UserDTO> people = Arrays.asList(
                new UserDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com"),
                new UserDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com"));
        booking.setPeople(people);
        booking.setPaymentMethod(PaymentDTOFixture.defaultDebitPayment());
        return booking;
    }

    public static BookingDTO defaultBookingWrongRoom() {
        BookingDTO booking = new BookingDTO();
        booking.setHotelCode("HB-0001");
        booking.setDateFrom("10/02/2021");
        booking.setDateTo("20/02/2021");
        booking.setDestination("Buenos Aires");
        booking.setPeopleAmount(1);
        booking.setRoomType("TRIPLE");
        List<UserDTO> people = Arrays.asList(
                new UserDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com"),
                new UserDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com"));
        booking.setPeople(people);
        booking.setPaymentMethod(PaymentDTOFixture.defaultCreditPayment());
        return booking;
    }

    public static BookingDTO defaultBookingWrongDestination() {
        BookingDTO booking = new BookingDTO();
        booking.setHotelCode("HB-0001");
        booking.setDateFrom("10/02/2021");
        booking.setDateTo("20/02/2021");
        booking.setDestination("Formosa");
        booking.setPeopleAmount(1);
        booking.setRoomType("SINGLE");
        List<UserDTO> people = Arrays.asList(
                new UserDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com"),
                new UserDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com"));
        booking.setPeople(people);
        booking.setPaymentMethod(PaymentDTOFixture.defaultCreditPayment());
        return booking;
    }

    public static BookingDTO defaultBookingTooLate() {
        BookingDTO booking = new BookingDTO();
        booking.setHotelCode("HB-0001");
        booking.setDateFrom("10/02/2021");
        booking.setDateTo("16/07/2023");
        booking.setDestination("Buenos Aires");
        booking.setPeopleAmount(1);
        booking.setRoomType("SINGLE");
        List<UserDTO> people = Arrays.asList(
                new UserDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com"),
                new UserDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com"));
        booking.setPeople(people);
        booking.setPaymentMethod(PaymentDTOFixture.defaultCreditPayment());
        return booking;
    }

    public static BookingDTO defaultBookingTooEarly() {
        BookingDTO booking = new BookingDTO();
        booking.setHotelCode("HB-0001");
        booking.setDateFrom("03/11/2018");
        booking.setDateTo("20/02/2021");
        booking.setDestination("Buenos Aires");
        booking.setPeopleAmount(1);
        booking.setRoomType("SINGLE");
        List<UserDTO> people = Arrays.asList(
                new UserDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com"),
                new UserDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com"));
        booking.setPeople(people);
        booking.setPaymentMethod(PaymentDTOFixture.defaultCreditPayment());
        return booking;
    }

    public static BookingDTO defaultBookingInvalidDates() {
        BookingDTO booking = new BookingDTO();
        booking.setHotelCode("HB-0001");
        booking.setDateFrom("10/02/2021");
        booking.setDateTo("20-02-2021");
        booking.setDestination("Buenos Aires");
        booking.setPeopleAmount(1);
        booking.setRoomType("SINGLE");
        List<UserDTO> people = Arrays.asList(
                new UserDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com"),
                new UserDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com"));
        booking.setPeople(people);
        booking.setPaymentMethod(PaymentDTOFixture.defaultCreditPayment());
        return booking;
    }

    public static BookingDTO defaultBookingAlreadyReserved() {
        BookingDTO booking = new BookingDTO();
        booking.setHotelCode("BG-0004");
        booking.setDateFrom("18/04/2021");
        booking.setDateTo("20/04/2021");
        booking.setDestination("Cartagena");
        booking.setPeopleAmount(7);
        booking.setRoomType("múltiple");
        List<UserDTO> people = Arrays.asList(
                new UserDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com"),
                new UserDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com"));
        booking.setPeople(people);
        booking.setPaymentMethod(PaymentDTOFixture.defaultCreditPayment());
        return booking;
    }

    public static BookingDTO defaultBookingWrongCode() {
        BookingDTO booking = new BookingDTO();
        booking.setHotelCode("ZZ-1111");
        booking.setDateFrom("10/02/2021");
        booking.setDateTo("20/02/2021");
        booking.setDestination("Buenos Aires");
        booking.setPeopleAmount(1);
        booking.setRoomType("SINGLE");
        List<UserDTO> people = Arrays.asList(
                new UserDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com"),
                new UserDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com"));
        booking.setPeople(people);
        booking.setPaymentMethod(PaymentDTOFixture.defaultCreditPayment());
        return booking;
    }

    public static BookingDTO defaultBookingDTO() {
        BookingDTO booking = new BookingDTO();
        booking.setHotelCode("HB-0001");
        booking.setDateFrom("10/02/2021");
        booking.setDateTo("20/02/2021");
        booking.setDestination("Buenos Aires");
        booking.setPeopleAmount(1);
        booking.setRoomType("SINGLE");
        List<UserDTO> people = Arrays.asList(
                new UserDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com"),
                new UserDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com"));
        booking.setPeople(people);
        booking.setPaymentMethod(PaymentDTOFixture.defaultCreditPayment());
        return booking;
    }

    public static BookingResponseDTO defaultBookingResponseDTO() {
        BookingResponseDTO booking = new BookingResponseDTO();
        booking.setHotelCode("HB-0001");
        booking.setDateFrom("10/02/2021");
        booking.setDateTo("20/02/2021");
        booking.setDestination("Buenos Aires");
        booking.setPeopleAmount(1);
        booking.setRoomType("SINGLE");
        List<UserDTO> people = Arrays.asList(
                new UserDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com"),
                new UserDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com"));
        booking.setPeople(people);
        return booking;
    }


}
