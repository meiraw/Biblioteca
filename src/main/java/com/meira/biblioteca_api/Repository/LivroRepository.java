package com.meira.biblioteca_api.Repository;

import com.meira.biblioteca_api.Enums.StatusLivro;
import com.meira.biblioteca_api.Model.LivroModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<LivroModel, UUID> {
    List<LivroModel> findByTituloContainingIgnoreCase(String titulo);
    Page<LivroModel> findByStatus(StatusLivro status, Pageable pageable);
}
