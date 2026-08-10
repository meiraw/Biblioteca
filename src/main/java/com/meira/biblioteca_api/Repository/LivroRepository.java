package com.meira.biblioteca_api.Repository;

import com.meira.biblioteca_api.Enums.StatusLivro;
import com.meira.biblioteca_api.Model.LivroModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;
// JpaSpecificationExecutor<LivroModel>  faz consultas dinâmicas
public interface LivroRepository extends JpaRepository<LivroModel, UUID>, JpaSpecificationExecutor<LivroModel> { // Aplicando o Specification
    List<LivroModel> findByTituloContainingIgnoreCase(String titulo);
    Page<LivroModel> findByStatus(StatusLivro status, Pageable pageable);

}
