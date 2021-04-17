package com.desafio.reservas.repositories;

import com.desafio.reservas.dtos.FlightDTO;

import java.util.List;

public interface FlightRepository {

    public List<FlightDTO> loadFlights(String path);
}
