package com.meira.biblioteca_api.DTO.RequestDTO;

import com.meira.biblioteca_api.Enums.StatusEmprestimo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class EmprestimoRequestDTO {

    @NotBlank(message = "O livro não pode ser vazio!")
    private UUID livroId;
    @NotNull (message = "A data não pode ser nula!")
    private LocalDate dataEmprestimo;
    @NotNull (message = "A data não pode ser nula!")
    private LocalDate dataDevolucao;
    @NotBlank(message = "O livro não pode ser vazio!")
    private StatusEmprestimo emprestimo;

}
