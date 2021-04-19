package com.desafio.reservas.controllers;

import com.desafio.reservas.dtos.PayloadHotelDTO;
import com.desafio.reservas.dtos.StatusDTO;
import com.desafio.reservas.exceptions.HotelException;
import com.desafio.reservas.services.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class HotelController {

    private HotelService service;

    public HotelController(HotelService service) {
        this.service = service;
    }

    @GetMapping("/hotels")
    public ResponseEntity listHotels(@RequestParam(required = false) Map<String, String> params) throws HotelException {
        return new ResponseEntity(service.listHotelsAvailable(params), HttpStatus.OK);
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
