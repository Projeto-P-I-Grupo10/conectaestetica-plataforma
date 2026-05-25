package school.sptech.cursos.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cursos.DTO.AvaliacaoCurso.AvaliacaoCursoRequest;
import school.sptech.cursos.DTO.AvaliacaoCurso.AvaliacaoCursoResponse;
import school.sptech.cursos.service.AvaliacaoCursoService;


import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/avaliacaoCurso")

public class AvaliacaoCursoController {

    private final AvaliacaoCursoService service;

    public AvaliacaoCursoController(AvaliacaoCursoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AvaliacaoCursoResponse>> listarAvaliacoes() {
        return ResponseEntity.ok(service.listarAvaliacoes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoCursoResponse> buscarPorId(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AvaliacaoCursoResponse> criar(
            @RequestBody @Valid AvaliacaoCursoRequest request
    ) {
        return ResponseEntity
                .status(201)
                .body(service.criarAvaliacao(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoCursoResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid AvaliacaoCursoRequest request
    ) {
        return ResponseEntity.ok(
                service.atualizarAvaliacao(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id
    ) {
        service.deletarAvaliacao(id);
        return ResponseEntity.noContent().build();
    }
}
