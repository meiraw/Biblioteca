package com.meira.biblioteca_api.DTO.RequestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data

public class AutorRequestDTO {

    @NotBlank(message = "O nome do autor não pode ser vazio!")
    private String nome;

    @NotNull(message = "Idade não pode ser nula!")
    @Positive(message = " A idade tem que ser maio que zero!")
    private Integer idade;
}
