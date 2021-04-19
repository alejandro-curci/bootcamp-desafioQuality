package com.desafio.reservas.fixtures;

import com.desafio.reservas.dtos.FlightDTO;
import com.desafio.reservas.dtos.FlightFormatDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FlightDTOFixture {

    public static List<FlightFormatDTO> defaultFormattedFlights() {
        List<FlightFormatDTO> flights = new ArrayList<>();
        flights.add(FlightDTOFixture.defaultFormattedFlight1());
        flights.add(FlightDTOFixture.defaultFormattedFlight2());
        flights.add(FlightDTOFixture.defaultFormattedFlight3());
        flights.add(FlightDTOFixture.defaultFormattedFlight4());
        return flights;
    }

    public static List<FlightDTO> defaultFlights() {
        List<FlightDTO> flights = new ArrayList<>();
        flights.add(FlightDTOFixture.defaultFlight1());
        flights.add(FlightDTOFixture.defaultFlight2());
        flights.add(FlightDTOFixture.defaultFlight3());
        flights.add(FlightDTOFixture.defaultFlight4());
        return flights;
    }

    private static FlightFormatDTO defaultFormattedFlight4() {
        FlightFormatDTO flight = new FlightFormatDTO();
        flight.setFlightNumber("CAME-0321");
        flight.setOrigin("Cartagena");
        flight.setDestination("Medellín");
        flight.setSeatType("Economy");
        flight.setSeatPrice(7800);
        flight.setDateFrom("23/01/2021");
        flight.setDateTo("31/01/2021");
        return flight;
    }

    private static FlightDTO defaultFlight4() {
        FlightDTO flight = new FlightDTO();
        flight.setFlightNumber("CAME-0321");
        flight.setOrigin("Cartagena");
        flight.setDestination("Medellín");
        flight.setSeatType("Economy");
        flight.setSeatPrice(7800);
        flight.setDateFrom(LocalDate.parse("2021-01-23"));
        flight.setDateTo(LocalDate.parse("2021-01-31"));
        return flight;
    }

    private static FlightFormatDTO defaultFormattedFlight3() {
        FlightFormatDTO flight = new FlightFormatDTO();
        flight.setFlightNumber("PIBA-1428");
        flight.setOrigin("Puerto Iguazú");
        flight.setDestination("Bogotá");
        flight.setSeatType("Business");
        flight.setSeatPrice(43200);
        flight.setDateFrom("10/02/2021");
        flight.setDateTo("20/02/2021");
        return flight;
    }

    private static FlightDTO defaultFlight3() {
        FlightDTO flight = new FlightDTO();
        flight.setFlightNumber("PIBA-1428");
        flight.setOrigin("Puerto Iguazú");
        flight.setDestination("Bogotá");
        flight.setSeatType("Business");
        flight.setSeatPrice(43200);
        flight.setDateFrom(LocalDate.parse("2021-02-10"));
        flight.setDateTo(LocalDate.parse("2021-02-20"));
        return flight;
    }

    private static FlightFormatDTO defaultFormattedFlight2() {
        FlightFormatDTO flight = new FlightFormatDTO();
        flight.setFlightNumber("TUPI-3369");
        flight.setOrigin("Tucumán");
        flight.setDestination("Puerto Iguazú");
        flight.setSeatType("Business");
        flight.setSeatPrice(12530);
        flight.setDateFrom("12/02/2021");
        flight.setDateTo("23/02/2021");
        return flight;
    }

    private static FlightDTO defaultFlight2() {
        FlightDTO flight = new FlightDTO();
        flight.setFlightNumber("TUPI-3369");
        flight.setOrigin("Tucumán");
        flight.setDestination("Puerto Iguazú");
        flight.setSeatType("Business");
        flight.setSeatPrice(12530);
        flight.setDateFrom(LocalDate.parse("2021-02-12"));
        flight.setDateTo(LocalDate.parse("2021-02-23"));
        return flight;
    }

    private static FlightFormatDTO defaultFormattedFlight1() {
        FlightFormatDTO flight = new FlightFormatDTO();
        flight.setFlightNumber("BOBA-6567");
        flight.setOrigin("Bogotá");
        flight.setDestination("Buenos Aires");
        flight.setSeatType("Economy");
        flight.setSeatPrice(39860);
        flight.setDateFrom("01/03/2021");
        flight.setDateTo("14/03/2021");
        return flight;
    }

    private static FlightDTO defaultFlight1() {
        FlightDTO flight = new FlightDTO();
        flight.setFlightNumber("BOBA-6567");
        flight.setOrigin("Bogotá");
        flight.setDestination("Buenos Aires");
        flight.setSeatType("Economy");
        flight.setSeatPrice(39860);
        flight.setDateFrom(LocalDate.parse("2021-03-01"));
        flight.setDateTo(LocalDate.parse("2021-03-14"));
        return flight;
    }
}
