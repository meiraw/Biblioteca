package com.meira.biblioteca_api.Controller;

import com.meira.biblioteca_api.DTO.RequqestDTO.AutorRequestDTO;
import com.meira.biblioteca_api.DTO.RequqestDTO.LivroRequestDTO;
import com.meira.biblioteca_api.DTO.ResponseDTO.AutorResponseDTO;
import com.meira.biblioteca_api.DTO.ResponseDTO.LivroResponseDTO;
import com.meira.biblioteca_api.Model.AutorModel;
import com.meira.biblioteca_api.Model.LivroModel;
import com.meira.biblioteca_api.Service.AutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping
    public ResponseEntity<AutorResponseDTO> salvar (@Valid @RequestBody AutorRequestDTO dto){
        AutorModel autor  = autorService.criar(dto);
        return ResponseEntity.status(201).body(new AutorResponseDTO(autor));
    }

    @GetMapping
    public ResponseEntity<Page<AutorResponseDTO>> listar(Pageable pageable ){
        Page<AutorResponseDTO> autor = autorService.listarTudo(pageable);
        return ResponseEntity.ok(autor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorResponseDTO > buscar (@PathVariable UUID id ){
        AutorModel buscaroid = autorService.buscarPorId(id);
        return ResponseEntity.ok(new AutorResponseDTO(buscaroid));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorResponseDTO> atualizar (@Valid @RequestBody AutorRequestDTO dto , @PathVariable UUID id){
        AutorModel renome = autorService.atualizar(dto,id);
        return ResponseEntity.ok(new AutorResponseDTO(renome));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir (@PathVariable UUID id ){
        AutorModel deletar = autorService.buscarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
