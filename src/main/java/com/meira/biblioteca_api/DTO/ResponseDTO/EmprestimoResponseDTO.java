package com.meira.biblioteca_api.DTO.ResponseDTO;

import com.meira.biblioteca_api.Enums.StatusEmprestimo;
import com.meira.biblioteca_api.Model.EmprestimoModel;
import com.meira.biblioteca_api.Model.LivroModel;

import java.time.LocalDate;
import java.util.UUID;

public class EmprestimoResponseDTO {
    private UUID id;
    private UUID livroId;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private StatusEmprestimo emprestimo;

    public EmprestimoResponseDTO (EmprestimoModel model){
        this.id = model.getId();
        this.livroId = model.getLivro().getId();
        this.dataEmprestimo = model.getDataEmprestimo();
        this.dataDevolucao = model.getDataDevolucao();
        this.emprestimo = model.getEmprestimo();
    }

    public UUID getId (){
        return id;
    }

    public UUID getLivroId (){
        return livroId;
    }

    public LocalDate getDataEmprestimo(){
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao(){
        return dataDevolucao;
    }

    public StatusEmprestimo getEmprestimo (){
        return emprestimo;
    }
}
