package com.desafio.reservas.controllers;

import com.desafio.reservas.dtos.PayloadFlightDTO;
import com.desafio.reservas.dtos.StatusDTO;
import com.desafio.reservas.exceptions.FlightException;
import com.desafio.reservas.services.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class FlightController {

    private FlightService service;

    public FlightController(FlightService service) {
        this.service = service;
    }

    @GetMapping("/flights")
    public ResponseEntity listFlights(@RequestParam(required = false) Map<String, String> params) throws FlightException {
        return new ResponseEntity(service.listFlightsAvailable(params), HttpStatus.OK);
    }

    @PostMapping("/flight-reservation")
    public ResponseEntity makeReservation(@RequestBody PayloadFlightDTO payload) throws FlightException {
        return new ResponseEntity(service.performReservation(payload), HttpStatus.OK);
    }

    @ExceptionHandler(FlightException.class)
    public ResponseEntity handleFlightException(FlightException e) {
        return new ResponseEntity(new StatusDTO(400, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
