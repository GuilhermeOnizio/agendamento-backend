package com.guilhermeonizio.AgendamentoConsultas.business;

import com.guilhermeonizio.AgendamentoConsultas.business.exception.ConsultaConflitoException;
import com.guilhermeonizio.AgendamentoConsultas.domain.Consulta;
import com.guilhermeonizio.AgendamentoConsultas.persistence.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    // Listar consultas
    public List<Consulta> listarConsultas() {
        return consultaRepository.findAll();
    }

    // Agendar nova consulta
    public Consulta agendarConsulta(Consulta consulta) {
        if (consulta.getDataHora().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("A data da consulta não pode ser no passado.");
        }

        if (existeConsultaParaMedicoNoMesmoHorario(consulta.getMedico().getId(), consulta.getDataHora())) {
            throw new ConsultaConflitoException("Já existe uma consulta agendada para este médico no mesmo horário.");
        }

        return consultaRepository.save(consulta);
    }

    private boolean existeConsultaParaMedicoNoMesmoHorario(Long medicoId, LocalDateTime dataHora) {
        return consultaRepository.existsByMedicoIdAndDataHora(medicoId, dataHora);
    }

    // Buscar consulta
    public Optional<Consulta> buscarConsultaPorId(Long id) {
        return consultaRepository.findById(id);
    }

    // Cancelar Consulta
    public void cancelarConsulta(Long id) {
        consultaRepository.deleteById(id);
    }

    // Listar consultas de um paciente
    public List<Consulta> listarConsultasPorPaciente(Long pacienteId) {
        return consultaRepository.findByPacienteId(pacienteId);
    }

    // Listar consultas por médico e data
    public List<Consulta> listarConsultasPorMedicoEData(Long medicoId, LocalDateTime dataHora) {
        return consultaRepository.findByMedicoIdAndDataHora(medicoId, dataHora);
    }

}
