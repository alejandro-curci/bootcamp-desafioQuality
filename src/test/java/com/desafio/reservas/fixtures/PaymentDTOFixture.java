package com.desafio.reservas.fixtures;

import com.desafio.reservas.dtos.PaymentDTO;

public class PaymentDTOFixture {

    public static PaymentDTO defaultCreditPayment3() {
        PaymentDTO payment = new PaymentDTO();
        payment.setType("CREDIT");
        payment.setNumber("1234-1234-1234-1234");
        payment.setDues(12);
        return payment;
    }
    public static PaymentDTO defaultCreditPayment2() {
        PaymentDTO payment = new PaymentDTO();
        payment.setType("CREDIT");
        payment.setNumber("1234-1234-1234-1234");
        payment.setDues(2);
        return payment;
    }

    public static PaymentDTO defaultCreditPayment() {
        PaymentDTO payment = new PaymentDTO();
        payment.setType("CREDIT");
        payment.setNumber("1234-1234-1234-1234");
        payment.setDues(6);
        return payment;
    }

    public static PaymentDTO defaultDebitPayment() {
        PaymentDTO payment = new PaymentDTO();
        payment.setType("DEBIT");
        payment.setNumber("1234-1234-1234-1234");
        payment.setDues(3);
        return payment;
    }
}
