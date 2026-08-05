package com.meira.biblioteca_api.DTO.RequestDTO;

import com.meira.biblioteca_api.Enums.StatusLivro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class LivroRequestDTO {


    @NotBlank(message = "O titulo não pode ser vazio!")
    @Size(min = 2 , max = 100 , message = " O titulo tem que ser de 2 à 100 caracteres!")
    private String titulo;

    @NotBlank(message = "A descrição não pode ser vazio!")
    @Size(min = 2 , max = 100 , message = " A descrição tem que ser de 2 à 100 caracteres!")
    private  String descricao;

    @NotNull(message = "O ano publicação não pode ser nulo!")
    @Positive(message = "O ano de publicação não pode vazio!")
    private Integer anopublicacao;

    @NotNull(message = "O status do livro  não pode ser nulo!")
    private StatusLivro status;

    @NotNull(message = "O autor não pode ser nulo!")
    private UUID autorid;


}
