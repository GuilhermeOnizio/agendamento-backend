package com.guilhermeonizio.AgendamentoConsultas.presentation;

import com.guilhermeonizio.AgendamentoConsultas.business.ConsultaService;
import com.guilhermeonizio.AgendamentoConsultas.domain.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    public List<Consulta> listarConsultas() {
        return consultaService.listarConsultas();
    }

    @PostMapping
    public Consulta agendarConsulta(@RequestBody Consulta consulta) {
        return consultaService.agendarConsulta(consulta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarConsultaPorId(@PathVariable Long id) {
        return consultaService.buscarConsultaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarConsulta(@PathVariable Long id) {
        consultaService.cancelarConsulta(id);
        return ResponseEntity.noContent().build();
    }

    // Listar consultas de um paciente
    @GetMapping("/paciente/{pacienteId}")
    public List<Consulta> listarConsultasPorPaciente(@PathVariable Long pacienteId) {
        return consultaService.listarConsultasPorPaciente(pacienteId);
    }

    // Listar consultas por médico e data
    @GetMapping("/medico/{medicoId}/data")
    public List<Consulta> listarConsultaPorMedicoEData(
            @PathVariable Long medicoId,
            @RequestParam LocalDateTime dataHora) {
        return consultaService.listarConsultasPorMedicoEData(medicoId, dataHora);
    }

}
