package com.desafio.reservas.fixtures;

import com.desafio.reservas.dtos.HotelDTO;
import com.desafio.reservas.dtos.HotelFormatDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelDTOFixture {

    public static List<HotelFormatDTO> defaultAvailableHotels() {
        List<HotelFormatDTO> hotels = new ArrayList<>();
        hotels.add(HotelDTOFixture.defaultFormattedHotel1());
        hotels.add(HotelDTOFixture.defaultFormattedHotel3());
        hotels.add(HotelDTOFixture.defaultFormattedHotel4());
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

    public static HotelFormatDTO defaultFormattedHotel4() {
        HotelFormatDTO hotel = new HotelFormatDTO();
        hotel.setHotelCode("HB-0001");
        hotel.setName("Hotel Bristol");
        hotel.setCity("Buenos Aires");
        hotel.setRoomType("Single");
        hotel.setPrice(5435);
        hotel.setAvailableFrom("10/02/2021");
        hotel.setAvailableTo("19/03/2021");
        hotel.setReserved("No");
        return hotel;
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

    public static HotelFormatDTO defaultFormattedHotel3() {
        HotelFormatDTO hotel = new HotelFormatDTO();
        hotel.setHotelCode("SH-0001");
        hotel.setName("Sheraton 2");
        hotel.setCity("Tucumán");
        hotel.setRoomType("Single");
        hotel.setPrice(4150);
        hotel.setAvailableFrom("02/01/2021");
        hotel.setAvailableTo("19/02/2021");
        hotel.setReserved("No");
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

    public static HotelFormatDTO defaultFormattedHotel2() {
        HotelFormatDTO hotel = new HotelFormatDTO();
        hotel.setHotelCode("BG-0004");
        hotel.setName("Bocagrande");
        hotel.setCity("Cartagena");
        hotel.setRoomType("Múltiple");
        hotel.setPrice(9370.0);
        hotel.setAvailableFrom("17/04/2021");
        hotel.setAvailableTo("12/06/2021");
        hotel.setReserved("Yes");
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

    public static HotelFormatDTO defaultFormattedHotel1() {
        HotelFormatDTO hotel = new HotelFormatDTO();
        hotel.setHotelCode("CH-0002");
        hotel.setName("Cataratas Hotel");
        hotel.setCity("Puerto Iguazú");
        hotel.setRoomType("Doble");
        hotel.setPrice(6300.0);
        hotel.setAvailableFrom("10/02/2021");
        hotel.setAvailableTo("20/03/2021");
        hotel.setReserved("No");
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
