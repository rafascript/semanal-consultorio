package com.projeto.consultoriomedico.repository;

import com.projeto.consultoriomedico.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, UUID> {
}
