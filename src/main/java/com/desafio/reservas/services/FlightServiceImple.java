package com.desafio.reservas.services;

import com.desafio.reservas.dtos.*;
import com.desafio.reservas.exceptions.FlightException;
import com.desafio.reservas.repositories.FlightRepository;
import com.desafio.reservas.utilities.Util;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FlightServiceImple implements FlightService {

    private FlightRepository repository;
    private Util util;

    public FlightServiceImple(FlightRepository repository) {
        this.repository = repository;
        this.util = new Util();
    }

    // returns available flights according user input
    @Override
    public List<FlightFormatDTO> listFlightsAvailable(Map<String, String> params) throws FlightException {
        String dateFrom = params.containsKey("dateFrom") ? params.get("dateFrom") : "";
        String dateTo = params.containsKey("dateTo") ? params.get("dateTo") : "";
        String origin = params.containsKey("origin") ? params.get("origin") : "";
        String destination = params.containsKey("destination") ? params.get("destination") : "";
        validateParameters(dateFrom, dateTo, origin, destination);
        List<FlightDTO> flights = repository.loadFlights("src/main/resources/flightsDB.csv");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // show flights between 3 days before and 3 days after the exact date required
        if (!dateFrom.isEmpty()) {
            LocalDate dateFromLocal = LocalDate.parse(dateFrom, formatter);
            flights.removeIf(f -> f.getDateFrom().isAfter(dateFromLocal.plusDays(3)));
            flights.removeIf(f -> f.getDateFrom().isBefore(dateFromLocal.minusDays(3)));
        }
        if (!dateTo.isEmpty()) {
            LocalDate dateToLocal = LocalDate.parse(dateTo, formatter);
            flights.removeIf(f -> f.getDateTo().isAfter(dateToLocal.plusDays(3)));
            flights.removeIf(f -> f.getDateTo().isBefore(dateToLocal.minusDays(3)));
        }
        if (!origin.isEmpty()) {
            flights.removeIf(f -> !f.getOrigin().equalsIgnoreCase(origin));
        }
        if (!destination.isEmpty()) {
            flights.removeIf(f -> !f.getDestination().equalsIgnoreCase(destination));
        }
        if (flights.isEmpty()) {
            throw new FlightException("No results for those filters");
        }
        List<FlightFormatDTO> flightsResponse = formatFlightList(flights);
        return flightsResponse;
    }

    // formats LocalDate and boolean fields to String
    public List<FlightFormatDTO> formatFlightList(List<FlightDTO> flights) {
        List<FlightFormatDTO> response = new ArrayList<>();
        for(FlightDTO f : flights) {
            FlightFormatDTO flightFormatDTO = new FlightFormatDTO();
            flightFormatDTO.setFlightNumber(f.getFlightNumber());
            flightFormatDTO.setOrigin(f.getOrigin());
            flightFormatDTO.setDestination(f.getDestination());
            flightFormatDTO.setSeatType(f.getSeatType());
            flightFormatDTO.setSeatPrice(f.getSeatPrice());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            flightFormatDTO.setDateFrom(f.getDateFrom().format(formatter));
            flightFormatDTO.setDateTo(f.getDateTo().format(formatter));
            response.add(flightFormatDTO);
        }
        return response;
    }

    // validates user input
    public void validateParameters(String dateFrom, String dateTo, String origin, String destination) throws FlightException {
        List<FlightDTO> flights = repository.loadFlights("src/main/resources/flightsDB.csv");
        // validate dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateFromLocal = null;
        LocalDate dateToLocal = null;
        if (!dateFrom.isEmpty()) {
            try {
                dateFromLocal = LocalDate.parse(dateFrom, formatter);
            } catch (DateTimeParseException e) {
                throw new FlightException("DateFrom is not valid");
            }
        }
        if (!dateTo.isEmpty()) {
            try {
                dateToLocal = LocalDate.parse(dateTo, formatter);
            } catch (DateTimeParseException e) {
                throw new FlightException("DateTo is not valid");
            }
        }
        if (dateFromLocal != null && dateToLocal != null) {
            if (dateFromLocal.isAfter(dateToLocal)) {
                throw new FlightException("Invalid dates. DateFrom must be before than DateTo");
            }
        }
        if (dateFromLocal != null) {
            LocalDate auxDate = LocalDate.parse(dateFrom, formatter);
            List<FlightDTO> dateFromList = flights.stream()
                    .filter(f -> f.getDateFrom().isBefore(auxDate.plusDays(3)))
                    .filter(f -> f.getDateFrom().isAfter(auxDate.minusDays(3)))
                    .collect(Collectors.toList());
            if (dateFromList.isEmpty()) {
                throw new FlightException("No results around Date From = " + dateFrom);
            }
        }
        if (dateToLocal != null) {
            LocalDate auxDate = LocalDate.parse(dateTo, formatter);
            List<FlightDTO> dateToList = flights.stream()
                    .filter(f -> f.getDateTo().isBefore(auxDate.plusDays(3)))
                    .filter(f -> f.getDateTo().isAfter(auxDate.minusDays(3)))
                    .collect(Collectors.toList());
            if (dateToList.isEmpty()) {
                throw new FlightException("No results around Date To = " + dateTo);
            }
        }
        // validates origin (only if it exists)
        if (!origin.isEmpty()) {
            List<FlightDTO> originList = flights.stream()
                    .filter(f -> f.getOrigin().equalsIgnoreCase(origin))
                    .collect(Collectors.toList());
            if (originList.isEmpty()) {
                throw new FlightException("Origin does not exist");
            }
        }
        // validates destination (only if it exists)
        if (!destination.isEmpty()) {
            List<FlightDTO> destinationList = flights.stream()
                    .filter(f -> f.getDestination().equalsIgnoreCase(destination))
                    .collect(Collectors.toList());
            if (destinationList.isEmpty()) {
                throw new FlightException("Destination does not exist");
            }
        }
    }

    @Override
    public ResponseFlightDTO performReservation(PayloadFlightDTO payload) throws FlightException {
        verifyReservation(payload);
        return createResponseDTO(payload);
    }

    // verifies whether the payload is valid or not
    public void verifyReservation(PayloadFlightDTO payload) throws FlightException {
        List<FlightDTO> flights = repository.loadFlights("src/main/resources/flightsDB.csv");
        flights.removeIf(f -> !f.getFlightNumber().equalsIgnoreCase(payload.getFlightReservation().getFlightNumber()));
        if (flights.isEmpty()) {
            throw new FlightException("The flight number is not valid");
        }
        FlightDTO flightData = flights.get(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (!util.validateDates(payload.getFlightReservation().getDateFrom(), payload.getFlightReservation().getDateTo())) {
            throw new FlightException("Dates are not valid.");
        }
        if (!flightData.getDateFrom().isEqual(LocalDate.parse(payload.getFlightReservation().getDateFrom(), formatter))) {
            throw new FlightException("DateFrom is not correct");
        }
        if (!flightData.getDateTo().isEqual(LocalDate.parse(payload.getFlightReservation().getDateTo(), formatter))) {
            throw new FlightException("DateTo is not correct");
        }
        if (!flightData.getOrigin().equalsIgnoreCase(payload.getFlightReservation().getOrigin())) {
            throw new FlightException("The origin is not correct");
        }
        if (!flightData.getDestination().equalsIgnoreCase(payload.getFlightReservation().getDestination())) {
            throw new FlightException("The destination is not correct");
        }
        if (payload.getFlightReservation().getSeats() > 4) {
            throw new FlightException("Too many people on the same ticket");
        }
        if (!flightData.getSeatType().equalsIgnoreCase(payload.getFlightReservation().getSeatType())) {
            throw new FlightException("The seat type is not correct");
        }
        if (!util.validateEmail(payload.getUserName())) {
            throw new FlightException("The email is not valid");
        }
        if (payload.getFlightReservation().getPaymentMethod().getType().equalsIgnoreCase("debit")) {
            if (payload.getFlightReservation().getPaymentMethod().getDues() > 1) {
                throw new FlightException("You cannot pay in many dues with DEBIT");
            }
        }
    }

    // creates the object required to send to the controller
    public ResponseFlightDTO createResponseDTO(PayloadFlightDTO payload) {
        ResponseFlightDTO response = new ResponseFlightDTO();
        response.setUserName(payload.getUserName());
        // round amount up to 2 decimals
        double amount = calculateAmount(payload.getFlightReservation());
        BigDecimal bdAmount = new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP);
        response.setAmount(bdAmount.doubleValue());
        response.setInterest(util.getInterest(payload.getFlightReservation().getPaymentMethod()));
        // round total up to 2 decimals
        double total = response.getAmount() * (1 + response.getInterest() / 100);
        BigDecimal bdTotal = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP);
        response.setTotal(bdTotal.doubleValue());
        response.setFlightReservation(getFlightReservationResponse(payload.getFlightReservation()));
        response.setStatusCode(new StatusDTO(200, "El proceso termino satisfactoriamente"));
        return response;
    }

    // calculates the amount to pay according to amount of tickets and the price per ticket
    public double calculateAmount(FlightReservationDTO reservation) {
        List<FlightDTO> flights = repository.loadFlights("src/main/resources/flightsDB.csv");
        flights.removeIf(f -> !f.getFlightNumber().equalsIgnoreCase(reservation.getFlightNumber()));
        return flights.get(0).getSeatPrice() * reservation.getSeats();
    }

    // FlightReservationResponseDTO has every same field from FlightReservationDTO but the methodPayment
    public FlightReservationResponseDTO getFlightReservationResponse(FlightReservationDTO reservation) {
        FlightReservationResponseDTO response = new FlightReservationResponseDTO();
        response.setFlightNumber(reservation.getFlightNumber());
        response.setDateFrom(reservation.getDateFrom());
        response.setDateTo(reservation.getDateTo());
        response.setOrigin(reservation.getOrigin());
        response.setDestination(reservation.getDestination());
        response.setSeats(reservation.getSeats());
        response.setSeatType(reservation.getSeatType());
        response.setPeople(reservation.getPeople());
        return response;
    }
}
