package com.desafio.reservas.services;

import com.desafio.reservas.dtos.*;
import com.desafio.reservas.exceptions.HotelException;
import com.desafio.reservas.repositories.HotelRepository;
import com.desafio.reservas.utilities.Util;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HotelServiceImple implements HotelService {

    private HotelRepository repository;
    private Util util;

    public HotelServiceImple(HotelRepository repository) {
        this.repository = repository;
        this.util = new Util();
    }

    // returns available hotels according to the filters
    @Override
    public List<HotelFormatDTO> listHotelsAvailable(Map<String, String> params) throws HotelException {
        String dateFrom = params.containsKey("dateFrom") ? params.get("dateFrom") : "";
        String dateTo = params.containsKey("dateTo") ? params.get("dateTo") : "";
        String destination = params.containsKey("destination") ? params.get("destination") : "";
        validateParameters(dateFrom, dateTo, destination);
        List<HotelDTO> hotels = repository.loadHotels("src/main/resources/hotelsDB.csv");
        hotels.removeIf(h -> h.isReserved());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (!dateFrom.isEmpty()) {
            LocalDate dateFromLocal = LocalDate.parse(dateFrom, formatter);
            hotels.removeIf(h -> h.getAvailableFrom().isAfter(dateFromLocal));
        }
        if (!dateTo.isEmpty()) {
            LocalDate dateToLocal = LocalDate.parse(dateTo, formatter);
            hotels.removeIf(h -> h.getAvailableTo().isBefore(dateToLocal));
        }
        if (!destination.isEmpty()) {
            hotels.removeIf(h -> !h.getCity().equalsIgnoreCase(destination));
        }
        List<HotelFormatDTO> hotelsResponse = formatHotelList(hotels);
        return hotelsResponse;
    }

    // formats LocalDate and boolean fields to String
    public List<HotelFormatDTO> formatHotelList(List<HotelDTO> hotels) {
        List<HotelFormatDTO> response = new ArrayList<>();
        for(HotelDTO h : hotels) {
            HotelFormatDTO hotelFormatDTO = new HotelFormatDTO();
            hotelFormatDTO.setHotelCode(h.getHotelCode());
            hotelFormatDTO.setName(h.getName());
            hotelFormatDTO.setCity(h.getCity());
            hotelFormatDTO.setRoomType(h.getRoomType());
            hotelFormatDTO.setPrice(h.getPrice());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            hotelFormatDTO.setAvailableFrom(h.getAvailableFrom().format(formatter));
            hotelFormatDTO.setAvailableTo(h.getAvailableTo().format(formatter));
            if(h.isReserved()) {
                hotelFormatDTO.setReserved("Yes");
            } else {
                hotelFormatDTO.setReserved("No");
            }
            response.add(hotelFormatDTO);
        }
        return response;
    }

    // validates user input
    public void validateParameters(String dateFrom, String dateTo, String destination) throws HotelException {
        List<HotelDTO> hotels = repository.loadHotels("src/main/resources/hotelsDB.csv");
        hotels.removeIf(h -> h.isReserved());
        // validate dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateFromLocal = null;
        LocalDate dateToLocal = null;
        if (!dateFrom.isEmpty()) {
            try {
                dateFromLocal = LocalDate.parse(dateFrom, formatter);
            } catch (DateTimeParseException e) {
                throw new HotelException("DateFrom is not valid");
            }
        }
        if (!dateTo.isEmpty()) {
            try {
                dateToLocal = LocalDate.parse(dateTo, formatter);
            } catch (DateTimeParseException e) {
                throw new HotelException("DateTo is not valid");
            }
        }
        if (dateFromLocal != null && dateToLocal != null) {
            if (dateFromLocal.isAfter(dateToLocal)) {
                throw new HotelException("Invalid dates. DateFrom must be before than DateTo");
            }
        }
        if (dateFromLocal != null) {
            LocalDate auxDate = LocalDate.parse(dateFrom, formatter);
            List<HotelDTO> dateFromList = hotels.stream()
                    .filter(h -> h.getAvailableFrom().isAfter(auxDate))
                    .collect(Collectors.toList());
            if (dateFromList.isEmpty()) {
                throw new HotelException("No results for Date From = " + dateFrom);
            }
        }
        if (dateToLocal != null) {
            LocalDate auxDate = LocalDate.parse(dateTo, formatter);
            List<HotelDTO> dateToList = hotels.stream()
                    .filter(h -> h.getAvailableTo().isBefore(auxDate))
                    .collect(Collectors.toList());
            if (dateToList.isEmpty()) {
                throw new HotelException("No results for Date To = " + dateTo);
            }
        }
        // validates destination (only if it exists)
        if (!destination.isEmpty()) {
            List<HotelDTO> destinationList = hotels.stream()
                    .filter(h -> h.getCity().equalsIgnoreCase(destination))
                    .collect(Collectors.toList());
            if (destinationList.isEmpty()) {
                throw new HotelException("Destination does not exist");
            }
        }
    }

    @Override
    public ResponseHotelDTO performBooking(PayloadHotelDTO payload) throws HotelException {
        verifyBooking(payload);
        return createResponseDTO(payload);
    }

    // verifies whether the payload is valid or not
    public void verifyBooking(PayloadHotelDTO payload) throws HotelException {
        List<HotelDTO> hotels = repository.loadHotels("src/main/resources/hotelsDB.csv");
        hotels.removeIf(h -> !h.getHotelCode().equalsIgnoreCase(payload.getBooking().getHotelCode()));
        if (hotels.isEmpty()) {
            throw new HotelException("The code hotel is not valid");
        }
        HotelDTO hotelData = hotels.get(0);
        if (hotelData.isReserved()) {
            throw new HotelException("The hotel is already reserved");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (!util.validateDates(payload.getBooking().getDateFrom(), payload.getBooking().getDateTo())) {
            throw new HotelException("Dates are not valid.");
        }
        if (hotelData.getAvailableFrom().isAfter(LocalDate.parse(payload.getBooking().getDateFrom(), formatter))) {
            throw new HotelException("The hotel gets available after your DateFrom");
        }
        if (hotelData.getAvailableTo().isBefore(LocalDate.parse(payload.getBooking().getDateTo(), formatter))) {
            throw new HotelException("The hotel needs to be available before your DateTo");
        }
        if (!hotelData.getCity().equalsIgnoreCase(payload.getBooking().getDestination())) {
            throw new HotelException("The destination does not exist or is mispelled");
        }
        boolean roomCondition = validateRoom(payload.getBooking().getPeopleAmount(), payload.getBooking().getRoomType());
        if (!roomCondition) {
            throw new HotelException("The amount of people does not match the type of room");
        }
        if (!util.validateEmail(payload.getUserName())) {
            throw new HotelException("The email is not valid");
        }
        if (payload.getBooking().getPaymentMethod().getType().equalsIgnoreCase("debit")) {
            if (payload.getBooking().getPaymentMethod().getDues() > 1) {
                throw new HotelException("You cannot pay in many dues with DEBIT");
            }
        }
    }

    // validates the amount of people and type of room
    public boolean validateRoom(int peopleAmount, String roomType) throws HotelException {
        if (peopleAmount == 1) {
            return roomType.equalsIgnoreCase("single");
        } else if (peopleAmount == 2) {
            return roomType.equalsIgnoreCase("doble");
        } else if (peopleAmount == 3) {
            return roomType.equalsIgnoreCase("triple");
        } else if (peopleAmount <= 10) {
            return roomType.equalsIgnoreCase("múltiple");
        } else {
            throw new HotelException("The amount of people exceeds the limit of 10 per room");
        }
    }

    // creates the object required to send to the controller
    public ResponseHotelDTO createResponseDTO(PayloadHotelDTO payload) throws HotelException {
        ResponseHotelDTO response = new ResponseHotelDTO();
        response.setUserName(payload.getUserName());
        // round amount up to 2 decimals
        double amount = calculateAmount(payload.getBooking());
        BigDecimal bdAmount = new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP);
        response.setAmount(bdAmount.doubleValue());
        response.setInterest(util.getInterest(payload.getBooking().getPaymentMethod()));
        // round total up to 2 decimals
        double total = response.getAmount() * (1 + response.getInterest() / 100);
        BigDecimal bdTotal = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP);
        response.setTotal(bdTotal.doubleValue());
        response.setBooking(getBookingResponse(payload.getBooking()));
        response.setStatusCode(new StatusDTO(200, "El proceso termino satisfactoriamente"));
        persistHotelReservation(response.getBooking().getHotelCode());
        return response;
    }

    // updates csv file with the new reservation
    public void persistHotelReservation(String hotelCode) throws HotelException {
        List<HotelDTO> hotels = repository.loadHotels("src/main/resources/hotelsDB.csv");
        for(HotelDTO h : hotels) {
            if(h.getHotelCode().equals(hotelCode)) {
                h.setReserved(true);
            }
        }
        repository.saveReservation(hotels, "src/main/resources/hotelsDB.csv");
    }

    // calculates the amount to pay according to amount of nights and the price per night
    public double calculateAmount(BookingDTO booking) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateFrom = LocalDate.parse(booking.getDateFrom(), formatter);
        LocalDate dateTo = LocalDate.parse(booking.getDateTo(), formatter);
        int nightsReserved = Period.between(dateFrom, dateTo).getDays();
        List<HotelDTO> hotels = repository.loadHotels("src/main/resources/hotelsDB.csv");
        hotels.removeIf(h -> !h.getHotelCode().equalsIgnoreCase(booking.getHotelCode()));
        double price = hotels.get(0).getPrice();
        return nightsReserved * price;
    }

    // BookingResponseDTO has every same field from BookingDTO but the methodPayment
    public BookingResponseDTO getBookingResponse(BookingDTO booking) {
        BookingResponseDTO response = new BookingResponseDTO();
        response.setHotelCode(booking.getHotelCode());
        response.setDateFrom(booking.getDateFrom());
        response.setDateTo(booking.getDateTo());
        response.setDestination(booking.getDestination());
        response.setPeopleAmount(booking.getPeopleAmount());
        response.setRoomType(booking.getRoomType());
        response.setPeople(booking.getPeople());
        return response;
    }
}
