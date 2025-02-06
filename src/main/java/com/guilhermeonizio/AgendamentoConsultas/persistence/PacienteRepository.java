package com.guilhermeonizio.AgendamentoConsultas.persistence;

import com.guilhermeonizio.AgendamentoConsultas.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
