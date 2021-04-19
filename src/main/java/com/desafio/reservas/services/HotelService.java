package com.desafio.reservas.services;

import com.desafio.reservas.dtos.HotelDTO;
import com.desafio.reservas.dtos.HotelFormatDTO;
import com.desafio.reservas.dtos.PayloadHotelDTO;
import com.desafio.reservas.dtos.ResponseHotelDTO;
import com.desafio.reservas.exceptions.HotelException;

import java.util.List;
import java.util.Map;

public interface HotelService {

    public List<HotelFormatDTO> listHotelsAvailable(Map<String, String> params) throws HotelException;

    public ResponseHotelDTO performBooking(PayloadHotelDTO payload) throws HotelException;
}
