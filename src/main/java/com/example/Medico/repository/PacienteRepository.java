package com.example.Medico.repository;

import com.example.Medico.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository  extends JpaRepository<Paciente, Long> {
}
