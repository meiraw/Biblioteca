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

    @ManyToOne   // Usamos para pode fazer o relacionamento JPA
    @JoinColumn(name = "Autor_id", nullable = false) // usamos esse código para pode ter um relacionamento entre tabelas
    // Sendo assim , estamos fazendo um relacionamento de livro com autor
    // no caso do autor , podemos fazer mesma coisa , porém , usamos o onetomany
    private AutorModel autor;

    //Um exemplo simples : vários podem ter uma pessoa , uma pessoa pode ter varios livros
    // assim que funciona o relacionamento JPA , o mesmo que tem entre entidade no banco de dados

}
