package com.desafio.reservas.utilities;

import com.desafio.reservas.fixtures.PaymentDTOFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    private Util util;

    @BeforeEach
    void setUp() {
        util = new Util();
    }

    @Test
    @DisplayName("Valid email")
    void validEmail() {
        boolean actual = util.validateEmail("pepito@yahoo.com");
        assertEquals(true, actual);
    }

    @Test
    @DisplayName("Invalid email")
    void invalidEmail() {
        boolean actual = util.validateEmail("pepito-yahoo.com.ar");
        assertEquals(false, actual);
    }

    @Test
    @DisplayName("Empty email")
    void emptyEmail() {
        boolean actual = util.validateEmail("");
        assertEquals(false, actual);
    }

    @Test
    @DisplayName("Valid dates")
    void successfulValidation() {
        boolean actual = util.validateDates("15/08/2020", "22/10/2020");
        assertEquals(true, actual);
    }

    @Test
    @DisplayName("Invalid dates (1/3)")
    void validateWrongFormat1() {
        boolean actual = util.validateDates("15/8/2020", "22/10/2020");
        assertEquals(false, actual);
    }

    @Test
    @DisplayName("Invalid dates (2/3)")
    void validateWrongFormat2() {
        boolean actual = util.validateDates("15/08/2020", "22-10-2020");
        assertEquals(false, actual);
    }

    @Test
    @DisplayName("Invalid dates (3/3)")
    void validateWrongFormat3() {
        boolean actual = util.validateDates("15/08/2020", "05/02/2020");
        assertEquals(false, actual);
    }

    @Test
    @DisplayName("Get the right interest (1/4)")
    void getInterest1() {
        double actual = util.getInterest(PaymentDTOFixture.defaultDebitPayment());
        assertEquals(0, actual);
    }

    @Test
    @DisplayName("Get the right interest (2/4)")
    void getInterest2() {
        double actual = util.getInterest(PaymentDTOFixture.defaultCreditPayment());
        assertEquals(10, actual);
    }

    @Test
    @DisplayName("Get the right interest (3/4)")
    void getInterest3() {
        double actual = util.getInterest(PaymentDTOFixture.defaultCreditPayment2());
        assertEquals(5, actual);
    }

    @Test
    @DisplayName("Get the right interest (4/4)")
    void getInterest4() {
        double actual = util.getInterest(PaymentDTOFixture.defaultCreditPayment3());
        assertEquals(15, actual);
    }
}