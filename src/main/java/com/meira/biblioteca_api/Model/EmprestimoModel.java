package com.meira.biblioteca_api.Model;

import com.meira.biblioteca_api.Enums.StatusEmprestimo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor

public class EmprestimoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "Livro_id", nullable = false)
    private LivroModel livro;

    private LocalDate dataEmprestimo;

    private  LocalDate dataDevolucao;

    @Enumerated(EnumType.STRING)
    private StatusEmprestimo emprestimo;
}
