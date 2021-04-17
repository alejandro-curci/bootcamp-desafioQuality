package com.desafio.reservas.dtos;

import lombok.Data;

@Data
public class PaymentDTO {
    private String type;
    private String number;
    private int dues;
}
