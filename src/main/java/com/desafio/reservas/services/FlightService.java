package com.desafio.reservas.services;

import com.desafio.reservas.dtos.*;
import com.desafio.reservas.exceptions.FlightException;

import java.util.List;

public interface FlightService {

    public List<FlightDTO> listFlightsAvailable(String dateFrom, String dateTo, String origin, String destination) throws FlightException;

    public ResponseFlightDTO performReservation(PayloadFlightDTO payload) throws FlightException;

}
