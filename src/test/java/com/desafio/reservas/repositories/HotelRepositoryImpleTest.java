package com.desafio.reservas.repositories;

import com.desafio.reservas.dtos.HotelDTO;
import com.desafio.reservas.exceptions.HotelException;
import com.desafio.reservas.fixtures.HotelDTOFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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

    @Test
    @DisplayName("Rewriting the hotel reservation file")
    void updateFile() throws HotelException {
        // expected file (hotelsDBTest.csv already harcoded)
        // actual file (hotelsDBTest2.csv, written into the file directly from the method)
        List<HotelDTO> hotels = HotelDTOFixture.defaultHotels();
        repository.saveReservation(hotels, "src/test/resources/hotelsDBTest2.csv");
        BufferedReader br1 = null;
        BufferedReader br2 = null;
        String sCurrentLine;
        try {
            List<String> expectedList = new ArrayList<>();
            List<String> actualList = new ArrayList<>();
            br1 = new BufferedReader(new FileReader("src/test/resources/hotelsDBTest.csv"));
            br2 = new BufferedReader(new FileReader("src/test/resources/hotelsDBTest2.csv"));
            while ((sCurrentLine = br1.readLine()) != null) {
                expectedList.add(sCurrentLine);
            }
            while ((sCurrentLine = br2.readLine()) != null) {
                actualList.add(sCurrentLine);
            }

            assertEquals(expectedList, actualList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}