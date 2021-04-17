package com.desafio.reservas.controllers;

import com.desafio.reservas.dtos.PayloadFlightDTO;
import com.desafio.reservas.dtos.StatusDTO;
import com.desafio.reservas.exceptions.FlightException;
import com.desafio.reservas.exceptions.HotelException;
import com.desafio.reservas.services.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class FlightController {

    // TODO server.servlet.contextPath=/api/vi
    // en application.properties se configura para evitar levantar dos instancias
    // TODO refactor duplicated code, add a "util" package
    // TODO refactor params in GET request, use Map<> to allow scalibility
    // TODO format LocalDate to String in the response of GET request
    // TODO HotelRepository - edit CSV with reservations
    // TODO add more tests / look for more coverage

    private FlightService service;

    public FlightController(FlightService service) {
        this.service = service;
    }

    // Para evitar enumerar cada RequestParam se puede usar un Map
    // @RequestParam Map<String, String> params
    // es mucho mas escalable de esta manera

    @GetMapping("/flights")
    public ResponseEntity listFlights(@RequestParam(required = false, defaultValue = "") String dateFrom,
                                     @RequestParam(required = false, defaultValue = "") String dateTo,
                                      @RequestParam(required = false, defaultValue = "") String origin,
                                     @RequestParam(required = false, defaultValue = "") String destination) throws FlightException {
        return new ResponseEntity(service.listFlightsAvailable(dateFrom, dateTo, origin, destination), HttpStatus.OK);
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
