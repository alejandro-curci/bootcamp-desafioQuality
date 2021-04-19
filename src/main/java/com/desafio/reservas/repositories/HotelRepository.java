package com.desafio.reservas.repositories;

import com.desafio.reservas.dtos.HotelDTO;
import com.desafio.reservas.exceptions.HotelException;

import java.util.List;

public interface HotelRepository {

    public List<HotelDTO> loadHotels(String path);

    public void saveReservation(List<HotelDTO> data, String path) throws HotelException;

}
