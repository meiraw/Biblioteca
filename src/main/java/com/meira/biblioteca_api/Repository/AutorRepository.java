package com.meira.biblioteca_api.Repository;

import com.meira.biblioteca_api.Model.AutorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutorRepository extends JpaRepository<AutorModel, UUID> {
}
