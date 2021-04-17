package com.desafio.reservas.services;

import com.desafio.reservas.dtos.HotelDTO;
import com.desafio.reservas.dtos.PayloadHotelDTO;
import com.desafio.reservas.dtos.ResponseHotelDTO;
import com.desafio.reservas.exceptions.HotelException;

import java.util.List;

public interface HotelService {

    public List<HotelDTO> listHotelsAvailable(String dateFrom, String dateTo,
                                              String destination) throws HotelException;

    public ResponseHotelDTO performBooking(PayloadHotelDTO payload) throws HotelException;

}
