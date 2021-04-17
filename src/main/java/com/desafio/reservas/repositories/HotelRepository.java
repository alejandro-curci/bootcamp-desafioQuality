package com.desafio.reservas.repositories;

import com.desafio.reservas.dtos.HotelDTO;

import java.util.List;

public interface HotelRepository {

    public List<HotelDTO> loadHotels(String path);
}
