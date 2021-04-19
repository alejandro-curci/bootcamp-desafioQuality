package com.desafio.reservas.repositories;

import com.desafio.reservas.dtos.HotelDTO;
import com.desafio.reservas.exceptions.HotelException;
import org.springframework.stereotype.Repository;

import java.io.*;
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
                    String number = fields[4].replaceAll("\\.", "").replaceAll("\\$", "");
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

    @Override
    public void saveReservation(List<HotelDTO> data, String path) throws HotelException {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            bw = new BufferedWriter(new FileWriter(path, false));
            bw.write("Código Hotel,Nombre,Lugar/Ciudad,Tipo de Habitación,Precio por noche,Disponible Desde,Disponible hasta,Reservado");
            bw.flush();
            for (HotelDTO h : data) {
                String entry = h.getHotelCode() + "," + h.getName() + "," + h.getCity() + "," + h.getRoomType() + ",";
                // price conversion from double to String
                entry += "$" + String.valueOf((int) h.getPrice()) + ",";
                // dates conversion from LocalDate to String
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                entry += h.getAvailableFrom().format(formatter) + "," + h.getAvailableTo().format(formatter) + ",";
                // reserved conversion from boolean to String
                if(h.isReserved()) {
                    entry += "SI";
                } else {
                    entry += "NO";
                }
                bw.write(System.lineSeparator() + entry);
                bw.flush();
            }
        } catch (IOException e) {
            throw new HotelException("Something went wrong. Reservation is not completed");
        } finally {
            if (fw != null)
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (bw != null)
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
