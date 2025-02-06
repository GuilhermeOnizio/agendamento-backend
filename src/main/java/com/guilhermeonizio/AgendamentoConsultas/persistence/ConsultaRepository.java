package com.guilhermeonizio.AgendamentoConsultas.persistence;

import com.guilhermeonizio.AgendamentoConsultas.domain.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
