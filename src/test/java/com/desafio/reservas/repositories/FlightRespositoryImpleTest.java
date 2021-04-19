package com.desafio.reservas.repositories;

import com.desafio.reservas.dtos.FlightDTO;
import com.desafio.reservas.fixtures.FlightDTOFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightRespositoryImpleTest {

    private FlightRepository repository;

    @BeforeEach
    void setUp() {
        repository = new FlightRespositoryImple();
    }

    @Test
    @DisplayName("Loading flight database")
    void loadFlightsCSV() {
        List<FlightDTO> actual = repository.loadFlights("src/test/resources/flightsDBTest.csv");
        List<FlightDTO> expected = FlightDTOFixture.defaultFlights();
        assertEquals(expected, actual);
    }
}