package com.guilhermeonizio.AgendamentoConsultas.business;

import com.guilhermeonizio.AgendamentoConsultas.business.exception.EmailDuplicadoException;
import com.guilhermeonizio.AgendamentoConsultas.domain.Medico;
import com.guilhermeonizio.AgendamentoConsultas.persistence.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico cadastrarMedico(Medico medico) {
        if (medicoRepository.existsByEmail(medico.getEmail())) {
            throw new EmailDuplicadoException("Já existe um médico cadastrado com este e-mail.");
        }
        return medicoRepository.save(medico);
    }

    public Optional<Medico> buscarMedicoPorId(Long id) {
        return medicoRepository.findById(id);
    }

    // Listar médicos por especialidade
    public List<Medico> listarMedicosPorEspecialidade(String especialidade) {
        return medicoRepository.findByEspecialidade(especialidade);
    }

    public Medico atualizarMedico(Long id, Medico medicoAtualizado) {
        return medicoRepository.findById(id)
                .map(medico -> {
                    medico.setNome(medicoAtualizado.getNome());
                    medico.setEspecialidade(medicoAtualizado.getEspecialidade());
                    return medicoRepository.save(medico);
                })
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));
    }

    public List<Medico> listarMedicos() {
        return medicoRepository.findAll();
    }

    public void deletarMedico(Long id) {
        medicoRepository.deleteById(id);
    }

}
