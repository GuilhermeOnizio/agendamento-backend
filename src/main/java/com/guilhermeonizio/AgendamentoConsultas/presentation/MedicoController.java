package com.guilhermeonizio.AgendamentoConsultas.presentation;

import com.guilhermeonizio.AgendamentoConsultas.business.MedicoService;
import com.guilhermeonizio.AgendamentoConsultas.domain.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    public Medico cadastrarMedico(@RequestBody Medico medico) {
        return medicoService.cadastrarMedico(medico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> buscarMedicoPorId(@PathVariable Long id) {
        return medicoService.buscarMedicoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/especialidade")
    public List<Medico> listarMedicosPorEspecialidade(@RequestParam String especialidade) {
        return medicoService.listarMedicosPorEspecialidade(especialidade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> atualizarMedico(
            @PathVariable Long id,
            @RequestBody Medico medicoAtualizado) {
        return ResponseEntity.ok(medicoService.atualizarMedico(id, medicoAtualizado));
    }

    @GetMapping
    public List<Medico> listarMedicos() {
        return medicoService.listarMedicos();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMedico(@PathVariable Long id) {
        medicoService.deletarMedico(id);
        return ResponseEntity.noContent().build();
    }

}
