package com.guilhermeonizio.AgendamentoConsultas.presentation;

import com.guilhermeonizio.AgendamentoConsultas.business.PacienteService;
import com.guilhermeonizio.AgendamentoConsultas.domain.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public Paciente cadastrarPaciente(@RequestBody Paciente paciente) {
        return pacienteService.cadastrarPaciente(paciente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable Long id) {
        return pacienteService.buscarPacientePorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizarPaciente(
            @PathVariable Long id,
            @RequestBody Paciente pacienteAtualizado) {
        return ResponseEntity.ok(pacienteService.atualizarPaciente(id, pacienteAtualizado));
    }

    @GetMapping
    public List<Paciente> listarPacientes() {
        return pacienteService.listarPacientes();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPaciente(@PathVariable Long id) {
        pacienteService.deletarPaciente(id);
        return ResponseEntity.noContent().build();
    }

}
