package com.meira.biblioteca_api.Controller;

import com.meira.biblioteca_api.DTO.RequestDTO.LivroRequestDTO;
import com.meira.biblioteca_api.DTO.ResponseDTO.LivroResponseDTO;
import com.meira.biblioteca_api.Enums.StatusLivro;
import com.meira.biblioteca_api.Model.LivroModel;
import com.meira.biblioteca_api.Service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


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

    @GetMapping("/buscar")
    public ResponseEntity<List<LivroResponseDTO>> buscarTitulo(@RequestParam String titulo){
        List<LivroModel> livros = livroservice. buscarPorTitulo(titulo); //Filtro de titulo

        List<LivroResponseDTO> resposta = livros.stream().map(LivroResponseDTO :: new ).toList();
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/status")
    public ResponseEntity<Page <LivroResponseDTO>> buscarstatus(@RequestParam StatusLivro status , Pageable pageable ){
        return ResponseEntity.ok(livroservice.buscarPorStatus(status,pageable)); //Filtro de status
    }

    @GetMapping("/buscar-specification")
    public ResponseEntity< List<LivroModel> >buscarSpecification (@RequestParam  String titulo){
        return ResponseEntity.ok(livroservice.buscarPorTituloSpecification(titulo));
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
