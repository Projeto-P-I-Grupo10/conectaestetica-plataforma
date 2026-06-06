package school.sptech.cursos.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cursos.dto.turma.TurmaRequest;
import school.sptech.cursos.dto.turma.TurmaResponse;
import school.sptech.cursos.projection.TurmaDetalhesProjection;
import school.sptech.cursos.service.TurmaService;

import java.util.List;

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

    @GetMapping("/detalhes")
    public ResponseEntity<List<TurmaDetalhesProjection>> listarDetalhes() {
        return ResponseEntity.ok(service.listarDetalhes());
    }

    @GetMapping("/detalhes-recentes")
    public ResponseEntity<List<TurmaDetalhesProjection>> listarDetalhesRecentes() {
        return ResponseEntity.ok(service.listarDetalhesRecentes());
    }

    @GetMapping("/detalhes-preco")
    public ResponseEntity<List<TurmaDetalhesProjection>> listarDetalhesPorPreco() {
        return ResponseEntity.ok(service.listarDetalhesPorPreco());
    }

    @GetMapping("/detalhes-avaliacao")
    public ResponseEntity<List<TurmaDetalhesProjection>> listarDetalhesPorAvaliacao() {
        return ResponseEntity.ok(service.listarDetalhesPorAvalicao());
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
