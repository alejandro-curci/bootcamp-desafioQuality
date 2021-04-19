package com.desafio.reservas.fixtures;

import com.desafio.reservas.dtos.FlightReservationDTO;
import com.desafio.reservas.dtos.FlightReservationResponseDTO;
import com.desafio.reservas.dtos.UserDTO;

import java.util.Arrays;
import java.util.List;

public class ReservationDTOFixture {

    // every method buils a BookingDTO object to use in a particular test

    public static FlightReservationDTO defaultFlightReservationDebitPayment() {
        FlightReservationDTO reservation = new FlightReservationDTO();
        reservation.setFlightNumber("PIBA-1428");
        reservation.setDateFrom("10/02/2021");
        reservation.setDateTo("20/02/2021");
        reservation.setOrigin("Puerto Iguazú");
        reservation.setDestination("Bogotá");
        reservation.setSeats(3);
        reservation.setSeatType("Business");
        List<UserDTO> people = Arrays.asList(
                new UserDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com"),
                new UserDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com"));
        reservation.setPeople(people);
        reservation.setPaymentMethod(PaymentDTOFixture.defaultDebitPayment());
        return reservation;
    }

    public static FlightReservationDTO defaultFlightReservationWrongSeatType() {
        FlightReservationDTO reservation = new FlightReservationDTO();
        reservation.setFlightNumber("PIBA-1428");
        reservation.setDateFrom("10/02/2021");
        reservation.setDateTo("20/02/2021");
        reservation.setOrigin("Puerto Iguazú");
        reservation.setDestination("Bogotá");
        reservation.setSeats(3);
        reservation.setSeatType("Economy");
        List<UserDTO> people = Arrays.asList(
                new UserDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com"),
                new UserDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com"));
        reservation.setPeople(people);
        reservation.setPaymentMethod(PaymentDTOFixture.defaultCreditPayment());
        return reservation;
    }

    public static FlightReservationDTO defaultFlightReservationTooManyPeople() {
        FlightReservationDTO reservation = new FlightReservationDTO();
        reservation.setFlightNumber("PIBA-1428");
        reservation.setDateFrom("10/02/2021");
        reservation.setDateTo("20/02/2021");
        reservation.setOrigin("Puerto Iguazú");
        reservation.setDestination("Bogotá");
        reservation.setSeats(7);
        reservation.setSeatType("Business");
        List<UserDTO> people = Arrays.asList(
                new UserDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com"),
                new UserDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com"));
        reservation.setPeople(people);
        reservation.setPaymentMethod(PaymentDTOFixture.defaultCreditPayment());
        return reservation;
    }

    public static FlightReservationDTO defaultFlightReservationWrongDestination() {
        FlightReservationDTO reservation = new FlightReservationDTO();
        reservation.setFlightNumber("PIBA-1428");
        reservation.setDateFrom("10/02/2021");
        reservation.setDateTo("20/02/2021");
        reservation.setOrigin("Puerto Iguazú");
        reservation.setDestination("Bariloche");
        reservation.setSeats(3);
        reservation.setSeatType("Business");
        List<UserDTO> people = Arrays.asList(
                new UserDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com"),
                new UserDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com"));
        reservation.setPeople(people);
        reservation.setPaymentMethod(PaymentDTOFixture.defaultCreditPayment());
        return reservation;
    }

    public static FlightReservationDTO defaultFlightReservationWrongOrigin() {
        FlightReservationDTO reservation = new FlightReservationDTO();
        reservation.setFlightNumber("PIBA-1428");
        reservation.setDateFrom("10/02/2021");
        reservation.setDateTo("20/02/2021");
        reservation.setOrigin("Miami");
        reservation.setDestination("Bogotá");
        reservation.setSeats(3);
        reservation.setSeatType("Business");
        List<UserDTO> people = Arrays.asList(
                new UserDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com"),
                new UserDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com"));
        reservation.setPeople(people);
        reservation.setPaymentMethod(PaymentDTOFixture.defaultCreditPayment());
        return reservation;
    }

    public static FlightReservationDTO defaultFlightReservationInvalidDateTo() {
        FlightReservationDTO reservation = new FlightReservationDTO();
        reservation.setFlightNumber("PIBA-1428");
        reservation.setDateFrom("10/02/2021");
        reservation.setDateTo("25/02/2021");
        reservation.setOrigin("Puerto Iguazú");
        reservation.setDestination("Bogotá");
        reservation.setSeats(3);
        reservation.setSeatType("Business");
        List<UserDTO> people = Arrays.asList(
                new UserDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com"),
                new UserDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com"));
        reservation.setPeople(people);
        reservation.setPaymentMethod(PaymentDTOFixture.defaultCreditPayment());
        return reservation;
    }

    public static FlightReservationDTO defaultFlightReservationInvalidDateFrom() {
        FlightReservationDTO reservation = new FlightReservationDTO();
        reservation.setFlightNumber("PIBA-1428");
        reservation.setDateFrom("09/02/2021");
        reservation.setDateTo("20/02/2021");
        reservation.setOrigin("Puerto Iguazú");
        reservation.setDestination("Bogotá");
        reservation.setSeats(3);
        reservation.setSeatType("Business");
        List<UserDTO> people = Arrays.asList(
                new UserDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com"),
                new UserDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com"));
        reservation.setPeople(people);
        reservation.setPaymentMethod(PaymentDTOFixture.defaultCreditPayment());
        return reservation;
    }

    public static FlightReservationDTO defaultFlightReservationInvalidDates() {
        FlightReservationDTO reservation = new FlightReservationDTO();
        reservation.setFlightNumber("PIBA-1428");
        reservation.setDateFrom("10-Feb-2021");
        reservation.setDateTo("20-Feb-2021");
        reservation.setOrigin("Puerto Iguazú");
        reservation.setDestination("Bogotá");
        reservation.setSeats(3);
        reservation.setSeatType("Business");
        List<UserDTO> people = Arrays.asList(
                new UserDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com"),
                new UserDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com"));
        reservation.setPeople(people);
        reservation.setPaymentMethod(PaymentDTOFixture.defaultCreditPayment());
        return reservation;
    }

    public static FlightReservationDTO defaultFlightReservationWrongFlightNumber() {
        FlightReservationDTO reservation = new FlightReservationDTO();
        reservation.setFlightNumber("ZZZZ-9832");
        reservation.setDateFrom("10/02/2021");
        reservation.setDateTo("20/02/2021");
        reservation.setOrigin("Puerto Iguazú");
        reservation.setDestination("Bogotá");
        reservation.setSeats(3);
        reservation.setSeatType("Business");
        List<UserDTO> people = Arrays.asList(
                new UserDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com"),
                new UserDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com"));
        reservation.setPeople(people);
        reservation.setPaymentMethod(PaymentDTOFixture.defaultCreditPayment());
        return reservation;
    }

    public static FlightReservationDTO defaultFlightReservationDTO() {
        FlightReservationDTO reservation = new FlightReservationDTO();
        reservation.setFlightNumber("PIBA-1428");
        reservation.setDateFrom("10/02/2021");
        reservation.setDateTo("20/02/2021");
        reservation.setOrigin("Puerto Iguazú");
        reservation.setDestination("Bogotá");
        reservation.setSeats(3);
        reservation.setSeatType("Business");
        List<UserDTO> people = Arrays.asList(
                new UserDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com"),
                new UserDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com"));
        reservation.setPeople(people);
        reservation.setPaymentMethod(PaymentDTOFixture.defaultCreditPayment());
        return reservation;
    }

    public static FlightReservationResponseDTO defaultFlightReservationResponseDTO() {
        FlightReservationResponseDTO reservation = new FlightReservationResponseDTO();
        reservation.setFlightNumber("PIBA-1428");
        reservation.setDateFrom("10/02/2021");
        reservation.setDateTo("20/02/2021");
        reservation.setOrigin("Puerto Iguazú");
        reservation.setDestination("Bogotá");
        reservation.setSeats(3);
        reservation.setSeatType("Business");
        List<UserDTO> people = Arrays.asList(
                new UserDTO("12345678", "Pepito", "Gomez", "10/11/1982", "pepitogomez@gmail.com"),
                new UserDTO("13345678", "Fulanito", "Gomez", "10/11/1983", "fulanitogomez@gmail.com"));
        reservation.setPeople(people);
        return reservation;
    }
}
