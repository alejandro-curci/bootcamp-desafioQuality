package com.desafio.reservas.fixtures;

import com.desafio.reservas.dtos.HotelDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelDTOFixture {

    public static List<HotelDTO> defaultAvailableHotels() {
        List<HotelDTO> hotels = new ArrayList<>();
        hotels.add(HotelDTOFixture.defaultHotel1());
        hotels.add(HotelDTOFixture.defaultHotel3());
        hotels.add(HotelDTOFixture.defaultHotel4());
        return hotels;
    }

    public static List<HotelDTO> defaultHotels() {
        List<HotelDTO> hotels = new ArrayList<>();
        hotels.add(HotelDTOFixture.defaultHotel1());
        hotels.add(HotelDTOFixture.defaultHotel2());
        hotels.add(HotelDTOFixture.defaultHotel3());
        hotels.add(HotelDTOFixture.defaultHotel4());
        return hotels;
    }

    public static HotelDTO defaultHotel4() {
        HotelDTO hotel = new HotelDTO();
        hotel.setHotelCode("HB-0001");
        hotel.setName("Hotel Bristol");
        hotel.setCity("Buenos Aires");
        hotel.setRoomType("Single");
        hotel.setPrice(5435);
        hotel.setAvailableFrom(LocalDate.parse("2021-02-10"));
        hotel.setAvailableTo(LocalDate.parse("2021-03-19"));
        hotel.setReserved(false);
        return hotel;
    }

    public static HotelDTO defaultHotel3() {
        HotelDTO hotel = new HotelDTO();
        hotel.setHotelCode("SH-0001");
        hotel.setName("Sheraton 2");
        hotel.setCity("Tucumán");
        hotel.setRoomType("Single");
        hotel.setPrice(4150);
        hotel.setAvailableFrom(LocalDate.parse("2021-01-02"));
        hotel.setAvailableTo(LocalDate.parse("2021-02-19"));
        hotel.setReserved(false);
        return hotel;
    }

    public static HotelDTO defaultHotel2() {
        HotelDTO hotel = new HotelDTO();
        hotel.setHotelCode("BG-0004");
        hotel.setName("Bocagrande");
        hotel.setCity("Cartagena");
        hotel.setRoomType("Múltiple");
        hotel.setPrice(9370.0);
        hotel.setAvailableFrom(LocalDate.parse("2021-04-17"));
        hotel.setAvailableTo(LocalDate.parse("2021-06-12"));
        hotel.setReserved(true);
        return hotel;
    }

    public static HotelDTO defaultHotel1() {
        HotelDTO hotel = new HotelDTO();
        hotel.setHotelCode("CH-0002");
        hotel.setName("Cataratas Hotel");
        hotel.setCity("Puerto Iguazú");
        hotel.setRoomType("Doble");
        hotel.setPrice(6300.0);
        hotel.setAvailableFrom(LocalDate.parse("2021-02-10"));
        hotel.setAvailableTo(LocalDate.parse("2021-03-20"));
        hotel.setReserved(false);
        return hotel;
    }
}
