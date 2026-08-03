package com.meira.biblioteca_api.DTO.ResponseDTO;

import com.meira.biblioteca_api.Model.AutorModel;

import java.util.List;
import java.util.UUID;

public class AutorResponseDTO {

    private UUID id;
    private String nome;
    private Integer idade;
    private List<LivroResponseDTO> livros;

    public AutorResponseDTO (AutorModel model){
        this.id = model.getId();
        this.nome = model.getNome();
        this.idade = model.getIdade();
        this.livros = model.getLivros()
                .stream()
                .map(LivroResponseDTO::new)
                .toList();
    }

    public UUID getId (){
        return id;
    }

    public String getNome (){
        return nome;
    }

    public Integer getIdade (){
        return idade;
    }

    public List<LivroResponseDTO> getLivros() {
        return livros;
    }


}
