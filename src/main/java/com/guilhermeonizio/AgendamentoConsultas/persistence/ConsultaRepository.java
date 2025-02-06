package com.guilhermeonizio.AgendamentoConsultas.persistence;

import com.guilhermeonizio.AgendamentoConsultas.domain.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    // Busca consultas por paciente (usando o ID do paciente)
    @Query("SELECT c FROM Consulta c WHERE c.paciente.id = :pacienteId")
    List<Consulta> findByPacienteId(@Param("pacienteId") Long pacienteId);

    // Busca consultas por médico e data
    @Query("SELECT c FROM Consulta c WHERE c.medico.id = :medicoId AND c.dataHora = :dataHora")
    List<Consulta> findByMedicoIdAndDataHora(@Param("medicoId") Long medicoId, @Param("dataHora") LocalDateTime dataHora);
}
