package school.sptech.cursos.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cursos.DTO.Turma.TurmaRequest;
import school.sptech.cursos.DTO.Turma.TurmaResponse;
import school.sptech.cursos.projection.TurmaDetalhesProjection;
import school.sptech.cursos.service.TurmaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaService service;

    public TurmaController(TurmaService service) {
        this.service = service;
    }

    @GetMapping("/detalhes/{id}")
    public ResponseEntity<TurmaDetalhesProjection> buscarDetalhes(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarDetalhes(id));
    }

    @PostMapping
    public ResponseEntity<TurmaResponse> criar(@RequestBody @Valid TurmaRequest request) {
        return ResponseEntity.status(201).body(service.adicionarTurma(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid TurmaRequest request
    ) {
        return ResponseEntity.ok(service.atualizarTurma(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.removerTurma(id);
        return ResponseEntity.noContent().build();
    }
}
