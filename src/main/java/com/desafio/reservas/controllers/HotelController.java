package com.desafio.reservas.controllers;

import com.desafio.reservas.dtos.PayloadHotelDTO;
import com.desafio.reservas.dtos.StatusDTO;
import com.desafio.reservas.exceptions.HotelException;
import com.desafio.reservas.services.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class HotelController {

    private HotelService service;

    public HotelController(HotelService service) {
        this.service = service;
    }

    @GetMapping("/hotels")
    public ResponseEntity listHotels(@RequestParam(required = false, defaultValue = "") String dateFrom,
                                     @RequestParam(required = false, defaultValue = "") String dateTo,
                                     @RequestParam(required = false, defaultValue = "") String destination) throws HotelException {
        return new ResponseEntity(service.listHotelsAvailable(dateFrom, dateTo, destination), HttpStatus.OK);
    }

    @PostMapping("/booking")
    public ResponseEntity bookHotel(@RequestBody PayloadHotelDTO payload) throws HotelException {
        return new ResponseEntity(service.performBooking(payload), HttpStatus.OK);
    }

    @ExceptionHandler(HotelException.class)
    public ResponseEntity handleHotelException(HotelException e) {
        return new ResponseEntity(new StatusDTO(400, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
