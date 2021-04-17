package com.desafio.reservas.repositories;

import com.desafio.reservas.dtos.HotelDTO;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HotelRepositoryImple implements HotelRepository {

    @Override
    public List<HotelDTO> loadHotels(String path) {
        List<HotelDTO> hotels = new ArrayList<>();
        BufferedReader br = null;
        int row = 0;
        try {
            br = new BufferedReader(new FileReader(path));
            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                if (row > 0) {
                    HotelDTO hotelDTO = new HotelDTO();
                    hotelDTO.setHotelCode(fields[0]);
                    hotelDTO.setName(fields[1]);
                    hotelDTO.setCity(fields[2]);
                    hotelDTO.setRoomType(fields[3]);
                    String number = fields[4].replaceAll("\\.","").replaceAll("\\$","");
                    hotelDTO.setPrice(Double.parseDouble(number));
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    hotelDTO.setAvailableFrom(LocalDate.parse(fields[5], formatter));
                    hotelDTO.setAvailableTo(LocalDate.parse(fields[6], formatter));
                    if (fields[7].equalsIgnoreCase("SI")) {
                        hotelDTO.setReserved(true);
                    } else {
                        hotelDTO.setReserved(false);
                    }
                    hotels.add(hotelDTO);
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
        return hotels;
    }
}
