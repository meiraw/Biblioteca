package com.meira.biblioteca_api.Service;

import com.meira.biblioteca_api.DTO.RequestDTO.AutorRequestDTO;
import com.meira.biblioteca_api.DTO.ResponseDTO.AutorResponseDTO;
import com.meira.biblioteca_api.Exception.RegraNegocioException;
import com.meira.biblioteca_api.Exception.ResourceNotFoundException;
import com.meira.biblioteca_api.Model.AutorModel;
import com.meira.biblioteca_api.Repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public AutorModel criar (AutorRequestDTO dto){
        AutorModel autor = new AutorModel();

        autor.setNome(dto.getNome());
        autor.setIdade(dto.getIdade());
        return autorRepository.save(autor);
    }

    public Page<AutorResponseDTO> listarTudo(Pageable pageable){  // O Pageable é somente usado em listar dados
        // caso os dados pode ser grande
        Page<AutorModel> autor = autorRepository.findAll(pageable);
        return autor.map(AutorResponseDTO::new);
    }

    public AutorModel buscarPorId (UUID id){
        return autorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("O id "+id +" não foi encontrado!"));

    }

    public AutorModel atualizar (AutorRequestDTO dto , UUID id){
        AutorModel renome = buscarPorId(id);
        renome.setNome(dto.getNome());
        renome.setIdade(dto.getIdade());
        return autorRepository.save(renome);
    }

    //Apicação da regre de negócio
    public void deletar (UUID id){
        AutorModel excluir = buscarPorId(id);
        if (!excluir.getLivros().isEmpty()){ // Se a lista de livros do autor NÃO estiver vazia, impeça a exclusão.
            // o ! significa negação , no caso , precisamos se seja negatico, já que retorna true
            throw new RegraNegocioException("Não é possível excluir um autor que possui livros cadastrados.");
        }
        autorRepository.delete(excluir);
    }
}
