package com.desafio.reservas.utilities;

import com.desafio.reservas.dtos.PaymentDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    // validates email
    public boolean validateEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern p = Pattern.compile(regex);
        if (email.isEmpty()) {
            return false;
        }
        Matcher m = p.matcher(email);
        return m.matches();
    }

    // validates date format
    public boolean validateDates(String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateFromLocal = null;
        LocalDate dateToLocal = null;
        try {
            dateFromLocal = LocalDate.parse(dateFrom, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        try {
            dateToLocal = LocalDate.parse(dateTo, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        if (dateFromLocal.isAfter(dateToLocal)) {
            return false;
        }
        return true;
    }

    // decides the interest to apply according to the amount of dues
    public double getInterest(PaymentDTO payment) {
        if (payment.getType().equalsIgnoreCase("debit")) {
            return 0.0;
        } else {
            int dues = payment.getDues();
            if (dues <= 3) {
                return 5.0;
            } else if (dues <= 6) {
                return 10.0;
            } else {
                return 15.0;
            }
        }
    }
}
