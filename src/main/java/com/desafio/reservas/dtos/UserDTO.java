package com.desafio.reservas.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private String dni;
    private String name;
    private String lastName;
    private String birthDate;
    private String mail;
}
