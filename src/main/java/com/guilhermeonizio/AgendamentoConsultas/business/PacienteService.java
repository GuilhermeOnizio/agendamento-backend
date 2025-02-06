package com.guilhermeonizio.AgendamentoConsultas.business;

import com.guilhermeonizio.AgendamentoConsultas.domain.Paciente;
import com.guilhermeonizio.AgendamentoConsultas.persistence.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente cadastrarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Optional<Paciente> buscarPaciente(Long id) {
        return pacienteRepository.findById(id);
    }

    public Paciente atualizarPaciente(Long id, Paciente pacienteAtualizado) {
        return pacienteRepository.findById(id)
                .map(paciente -> {
                    paciente.setNome(pacienteAtualizado.getNome());
                    paciente.setEmail(pacienteAtualizado.getEmail());
                    return pacienteRepository.save(paciente);
                })
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
    }

    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    public void deletarPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

}
