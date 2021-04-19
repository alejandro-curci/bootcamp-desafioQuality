package com.desafio.reservas.services;

import com.desafio.reservas.dtos.*;
import com.desafio.reservas.exceptions.FlightException;

import java.util.List;
import java.util.Map;

public interface FlightService {

    public List<FlightFormatDTO> listFlightsAvailable(Map<String, String> params) throws FlightException;

    public ResponseFlightDTO performReservation(PayloadFlightDTO payload) throws FlightException;

}
