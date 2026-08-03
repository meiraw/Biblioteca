package com.meira.biblioteca_api.Repository;

import com.meira.biblioteca_api.Model.LivroModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<LivroModel, UUID> {
}
