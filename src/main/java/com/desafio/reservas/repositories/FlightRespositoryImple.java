package com.desafio.reservas.repositories;

import com.desafio.reservas.dtos.FlightDTO;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightRespositoryImple implements FlightRepository {

    @Override
    public List<FlightDTO> loadFlights(String path) {
        List<FlightDTO> flights = new ArrayList<>();
        BufferedReader br = null;
        int row = 0;
        try {
            br = new BufferedReader(new FileReader(path));
            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                if (row > 0) {
                    FlightDTO flightDTO = new FlightDTO();
                    flightDTO.setFlightNumber(fields[0]);
                    flightDTO.setOrigin(fields[1]);
                    flightDTO.setDestination(fields[2]);
                    flightDTO.setSeatType(fields[3]);
                    String number = fields[4].replaceAll("\\.", "").replaceAll("\\$", "");
                    flightDTO.setSeatPrice(Double.parseDouble(number));
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    flightDTO.setDateFrom(LocalDate.parse(fields[5], formatter));
                    flightDTO.setDateTo(LocalDate.parse(fields[6], formatter));
                    flights.add(flightDTO);
                }
                line = br.readLine();
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flights;
    }
}
