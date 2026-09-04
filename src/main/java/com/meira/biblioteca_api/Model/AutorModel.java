package com.meira.biblioteca_api.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
public class AutorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer idade;

    @OneToMany(mappedBy =  "autor")
    private List<LivroModel > livros = new ArrayList<>( );

    //Aqui estamos usando o OnetoMany  como , por exemplo : um autor possui vários livros
    // neste caso ele esta fazendo o inverso de ManytoOne
    //SObre o List<LivroModel> , utilizamos ele para não ter loop de busca , já pode ter algum tipo de problema
    // de busca caso faça get no postman

}
