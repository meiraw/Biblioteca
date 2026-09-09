package com.meira.biblioteca_api.Service;

import com.meira.biblioteca_api.DTO.RequestDTO.EmprestimoRequestDTO;
import com.meira.biblioteca_api.Exception.ResourceNotFoundException;
import com.meira.biblioteca_api.Model.EmprestimoModel;
import com.meira.biblioteca_api.Model.LivroModel;
import com.meira.biblioteca_api.Repository.EmprestimoRepository;
import com.meira.biblioteca_api.Repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service

public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;


    public EmprestimoModel  criar (EmprestimoRequestDTO dto) {
        LivroModel livroEmprestimo = livroRepository.findById(dto.getLivroId()).orElseThrow(() -> new ResourceNotFoundException(" O id" + dto.getLivroId() + "não foi encontrado!"));

        EmprestimoModel criandoEmprestimo = new EmprestimoModel();

        criandoEmprestimo.setDataEmprestimo(dto.getDataEmprestimo());
        criandoEmprestimo.setDataDevolucao(dto.getDataDevolucao());
        criandoEmprestimo.setLivro(livroEmprestimo);

        return emprestimoRepository.save(criandoEmprestimo);
    }

    public EmprestimoModel buscarPorIdEmprestimo (UUID id){
        return emprestimoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException ("O id "+id+"não foi encontrado!"));
    }

    public EmprestimoModel atualizar (EmprestimoRequestDTO dto, UUID id  ){
        LivroModel novolivroEmprestimo = livroRepository.findById(dto.getLivroId()).orElseThrow(()- > new ResourceNotFoundException ("O id "+dto.getLivroId()+"não foi encontrado!"));
        EmprestimoModel novoEmprestimo = buscarPorIdEmprestimo(id);

        novoEmprestimo.setDataEmprestimo(dto.getDataEmprestimo());
        novoEmprestimo.setDataDevolucao(dto.getDataDevolucao());
        novoEmprestimo.setLivro(novolivroEmprestimo);
        return emprestimoRepository.save(novoEmprestimo);
    }
}
