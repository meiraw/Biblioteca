package com.meira.biblioteca_api.Repository;

import com.meira.biblioteca_api.Model.EmprestimoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmprestimoRepository extends JpaRepository<EmprestimoModel, UUID> {
}
