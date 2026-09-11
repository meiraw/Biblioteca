package com.meira.biblioteca_api.Controller;

import com.meira.biblioteca_api.DTO.RequestDTO.EmprestimoRequestDTO;
import com.meira.biblioteca_api.DTO.ResponseDTO.EmprestimoResponseDTO;
import com.meira.biblioteca_api.Model.EmprestimoModel;
import com.meira.biblioteca_api.Service.EmprestimoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping
    public ResponseEntity<EmprestimoResponseDTO> salvar (@Valid @RequestBody EmprestimoRequestDTO dto){
        EmprestimoModel emprestimo = emprestimoService.criar(dto);
        return ResponseEntity.status(201).body(new EmprestimoResponseDTO(emprestimo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoResponseDTO> buscarPorId (@PathVariable UUID id ){
        EmprestimoModel buscar = emprestimoService.buscarPorIdEmprestimo(id);
        return ResponseEntity.ok(new EmprestimoResponseDTO(buscar));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Page<EmprestimoResponseDTO>> listarTudo (Pageable pageable){
        Page<EmprestimoResponseDTO> listartudo = emprestimoService.listarEmprestimo(pageable);
        return ResponseEntity.ok(listartudo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmprestimoModel> atualizarEmprestimo (@Valid @PathVariable UUID id , @RequestBody EmprestimoRequestDTO dto){
        EmprestimoModel novoemprestimo = emprestimoService.atualizar(dto, id);
        return ResponseEntity.ok(novoemprestimo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmprestimoModel> excluir (@PathVariable UUID id ){
         emprestimoService.deletarEmprestimo(id);
        return ResponseEntity.noContent().build();
    }
}
