package com.meira.biblioteca_api.DTO.ResponseDTO;

import com.meira.biblioteca_api.Enums.StatusLivro;
import com.meira.biblioteca_api.Model.LivroModel;

import java.util.UUID;

public class LivroResponseDTO {

    private UUID id;
    private String titulo;
    private String descricao;
    private Integer anopublicacao;
    private StatusLivro status;
    private UUID autorid;
    private String autornome;


    public LivroResponseDTO  (LivroModel model){
        this.id = model.getId();
        this.titulo = model.getTitulo();
        this.descricao = model.getDescricao();
        this.anopublicacao = model.getAnopublicacao();
        this.status = model.getStatus();
        this.autorid = model.getAutor().getId();
        this.autornome = model.getAutor().getNome();
    }

    public UUID getId (){
        return id;
    }

    public String  getTitulo (){
        return titulo;
    }

    public String getDescricao (){
        return descricao;
    }

    public Integer getAnopublicacao (){
        return anopublicacao;
    }

    public StatusLivro getStatus (){
        return status;
    }

    public UUID getAutorId (){
        return autorid;
    }

    public String  getAutornome (){
        return autornome;
    }
}
