package com.meira.biblioteca_api.Model;

import com.meira.biblioteca_api.Enums.StatusLivro;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Data

public class LivroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Integer anopublicacao;

    @Column(nullable = false )
    @Enumerated(EnumType.STRING)
    private StatusLivro status;

    @ManyToOne
    @JoinColumn(name = "Autor_id", nullable = false)
    private AutorModel autor;

}
