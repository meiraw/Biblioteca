package com.meira.biblioteca_api.Controller;

import com.meira.biblioteca_api.DTO.RequqestDTO.LivroRequestDTO;
import com.meira.biblioteca_api.DTO.ResponseDTO.LivroResponseDTO;
import com.meira.biblioteca_api.Model.LivroModel;
import com.meira.biblioteca_api.Service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.web.servlet.function.ServerResponse.noContent;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livroservice;


    @PostMapping
    public ResponseEntity <LivroResponseDTO> salvar (@Valid @RequestBody LivroRequestDTO dto ){
        LivroModel livro = livroservice.criar(dto);
        return ResponseEntity.status(201).body(new LivroResponseDTO(livro));
    }

    @GetMapping
    public ResponseEntity<Page<LivroResponseDTO>> listar(Pageable pageable ){
    Page<LivroResponseDTO> livro = livroservice.listarTudo(pageable);
    return ResponseEntity.ok(livro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDTO > buscar (@PathVariable UUID id ){
        LivroModel buscaroid = livroservice.buscarPorId(id);
        return ResponseEntity.ok(new LivroResponseDTO(buscaroid));
    }

    @PutMapping("/{id}")
public ResponseEntity<LivroResponseDTO> atualizar (@Valid @RequestBody LivroRequestDTO dto , @PathVariable UUID id){
        LivroModel novolivro = livroservice.atualizar(dto,id);
        return ResponseEntity.ok(new LivroResponseDTO(novolivro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir (@PathVariable UUID id ){
        LivroModel deletar = livroservice.buscarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
