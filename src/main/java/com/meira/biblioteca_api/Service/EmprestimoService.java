package com.meira.biblioteca_api.Service;

import com.meira.biblioteca_api.DTO.RequestDTO.EmprestimoRequestDTO;
import com.meira.biblioteca_api.DTO.ResponseDTO.EmprestimoResponseDTO;
import com.meira.biblioteca_api.Enums.StatusEmprestimo;
import com.meira.biblioteca_api.Enums.StatusLivro;
import com.meira.biblioteca_api.Exception.RegraNegocioException;
import com.meira.biblioteca_api.Exception.ResourceNotFoundException;
import com.meira.biblioteca_api.Model.EmprestimoModel;
import com.meira.biblioteca_api.Model.LivroModel;
import com.meira.biblioteca_api.Repository.EmprestimoRepository;
import com.meira.biblioteca_api.Repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service

public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LivroService livroService;


    public EmprestimoModel  criar (EmprestimoRequestDTO dto) {
        LivroModel livroEmprestimo = livroRepository.findById(dto.getLivroId()).orElseThrow(() -> new ResourceNotFoundException(" O id" + dto.getLivroId() + "não foi encontrado!"));

        EmprestimoModel criandoEmprestimo = new EmprestimoModel();

        criandoEmprestimo.setDataEmprestimo(dto.getDataEmprestimo());
        criandoEmprestimo.setDataDevolucao(dto.getDataDevolucao());
        criandoEmprestimo.setLivro(livroEmprestimo);

        return emprestimoRepository.save(criandoEmprestimo);
    }

    public Page<EmprestimoResponseDTO> listarEmprestimo (Pageable pageable) {
        Page<EmprestimoModel> listar = emprestimoRepository.findAll(pageable);
        return listar.map(EmprestimoResponseDTO :: new );
    }
    public EmprestimoModel buscarPorIdEmprestimo (UUID id){
        return emprestimoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException ("O id "+id+"não foi encontrado!"));
    }

    public EmprestimoModel atualizar (EmprestimoRequestDTO dto, UUID id  ){
        LivroModel novolivroEmprestimo = livroRepository.findById(dto.getLivroId()).orElseThrow(() -> new ResourceNotFoundException ("O id "+dto.getLivroId()+"não foi encontrado!"));
        EmprestimoModel novoEmprestimo = buscarPorIdEmprestimo(id);

        novoEmprestimo.setDataEmprestimo(dto.getDataEmprestimo());
        novoEmprestimo.setDataDevolucao(dto.getDataDevolucao());
        novoEmprestimo.setLivro(novolivroEmprestimo);
        return emprestimoRepository.save(novoEmprestimo);
    }

    public void deletarEmprestimo (UUID id){
        EmprestimoModel deletar = buscarPorIdEmprestimo(id);
        emprestimoRepository.delete(deletar);
    }

    @Transactional
    //Aplicando o metodo Transacional
    public EmprestimoModel emprestar (UUID livroid){
        LivroModel livro = livroService.buscarPorId(livroid);
        if(livro.getStatus() != StatusLivro.DISPONIVEL ){
            throw new RegraNegocioException("o livro não está disponivel para empréstimo!");
        }
        EmprestimoModel emprestimo = new EmprestimoModel();

        emprestimo.setLivro(livro);
        emprestimo.setEmprestimo(StatusEmprestimo.ATIVO);
        emprestimo.setDataEmprestimo(LocalDate.now ());
        emprestimo.setDataDevolucao(null);

        livro.setStatus(StatusLivro.EMPRESTADO);
        livroRepository.save(livro);
        return emprestimoRepository.save(emprestimo);
    }
}
