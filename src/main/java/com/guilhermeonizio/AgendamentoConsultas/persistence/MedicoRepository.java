package com.guilhermeonizio.AgendamentoConsultas.persistence;

import com.guilhermeonizio.AgendamentoConsultas.domain.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
