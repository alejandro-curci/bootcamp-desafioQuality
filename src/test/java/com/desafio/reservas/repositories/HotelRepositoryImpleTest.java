package com.desafio.reservas.repositories;

import com.desafio.reservas.dtos.HotelDTO;
import com.desafio.reservas.fixtures.HotelDTOFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HotelRepositoryImpleTest {

    private HotelRepositoryImple repository;

    @BeforeEach
    void setUp() {
        repository = new HotelRepositoryImple();
    }

    @Test
    @DisplayName("Loading hotel database")
    void loadHotels() {
        List<HotelDTO> actual = repository.loadHotels("src/test/resources/hotelsDBTest.csv");
        List<HotelDTO> expected = HotelDTOFixture.defaultHotels();

        assertEquals(expected, actual);
    }
}