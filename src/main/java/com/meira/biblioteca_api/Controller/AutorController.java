package com.meira.biblioteca_api.Controller;

import com.meira.biblioteca_api.DTO.RequestDTO.AutorRequestDTO;
import com.meira.biblioteca_api.DTO.ResponseDTO.AutorResponseDTO;
import com.meira.biblioteca_api.Model.AutorModel;
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

    // ResponseEntity , podemos usar para controlar a resposta em HTTP

    //Exemplos
    // 200 ok
    // 201 crated
    //  400 bad request
    // 404 Not found



    @Autowired
    private AutorService autorService;

    @PostMapping
    public ResponseEntity<AutorResponseDTO> salvar (@Valid @RequestBody AutorRequestDTO dto){
        AutorModel autor  = autorService.criar(dto);
        return ResponseEntity.status(201).body(new AutorResponseDTO(autor));
    }


    // o Pageable é utilzado somente em listar um dados inteiro

    @GetMapping
    public ResponseEntity<Page<AutorResponseDTO>> listar(Pageable pageable ){ // O uso do Pageable
        // é uma paginação de busca ,evita poder ter uma busca completa
        // quando temos uma busca grande de dados , é possivel que o programa trave
        //Sendo assim , usamos o page como forma de controlar isso
        // Ele é aplicado no controller e service
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
