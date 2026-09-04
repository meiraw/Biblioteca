package com.meira.biblioteca_api.Service;

import com.meira.biblioteca_api.DTO.RequestDTO.LivroRequestDTO;
import com.meira.biblioteca_api.DTO.ResponseDTO.LivroResponseDTO;
import com.meira.biblioteca_api.Enums.StatusLivro;
import com.meira.biblioteca_api.Exception.ResourceNotFoundException;
import com.meira.biblioteca_api.Model.AutorModel;
import com.meira.biblioteca_api.Model.LivroModel;
import com.meira.biblioteca_api.Repository.AutorRepository;
import com.meira.biblioteca_api.Repository.LivroRepository;
import com.meira.biblioteca_api.Specification.LivroSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

 @Service

public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    public LivroModel criar (LivroRequestDTO dto){

        AutorModel autor = autorRepository.findById(dto.getAutorid()).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Autor com id " + dto.getAutorid() +
                                " não encontrado!"));

        LivroModel livro = new LivroModel();

        livro.setTitulo(dto.getTitulo());
        livro.setDescricao(dto.getDescricao());
        livro.setAnopublicacao(dto.getAnopublicacao());
        livro.setStatus(dto.getStatus());
        livro.setAutor(autor);

        return livroRepository.save(livro);
    }

    public Page<LivroResponseDTO> listarTudo (Pageable pageable ){ //Paginação
        Page<LivroModel> livro = livroRepository.findAll(pageable);
        return livro.map(LivroResponseDTO::new);
    }

    public LivroModel buscarPorId (UUID id ){
        return livroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("O id "+id +" não foi encontrado! "));
    }

    public List <LivroModel> buscarPorTitulo(String titulo){  // Filtro de buscar por titulo
        return livroRepository.findByTituloContainingIgnoreCase(titulo); // Usando Containing e IgnoreCase
    }

     // Aplicação do metodo do specification no service para buscar de titulos
     // Nesse caso , ele recebe a string titulo e retorna em list<LivroModel>
    public List<LivroResponseDTO> buscarPorTituloSpecification  (String titulo ) { // Cria o metodo no service , onde iremos buscar o tiulo por specification

        Specification<LivroModel> specification = LivroSpecification.tituloContem(titulo);
        //Essa é a parte mais importante do metodo
        //acontence a conexão do speficication no repository com o service , onde pedimos ao repository o titulo e os metodos juntos.

        List<LivroModel> livros =
                livroRepository.findAll(specification);
        return livros.stream().map(LivroResponseDTO::new).toList();
    }
     public Page <LivroResponseDTO> buscarPorStatus(StatusLivro status, Pageable pageable ){
         return livroRepository.findByStatus(status, pageable ).map(LivroResponseDTO :: new );
     } //Usando paginação para reduzir a buscar completa dos livro na filtragem


    public LivroModel atualizar (LivroRequestDTO dto ,UUID id){

        AutorModel renome = autorRepository.findById(dto.getAutorid()).orElseThrow(()-> new ResourceNotFoundException ("O id "+ dto.getAutorid() +" não foi encontrado!"));

        LivroModel novolivro = buscarPorId(id);

        novolivro.setTitulo(dto.getTitulo());
        novolivro.setDescricao(dto.getDescricao());
        novolivro.setAnopublicacao(dto.getAnopublicacao());
        novolivro.setStatus(dto.getStatus());
        novolivro.setAutor(renome);
        return livroRepository.save(novolivro);
    }

    public void deletar(UUID id ){
        LivroModel excluir = buscarPorId(id);
        livroRepository.delete(excluir);
    }


}
