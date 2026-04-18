package school.sptech.cursos.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cursos.DTO.Professor.ProfessorRequest;
import school.sptech.cursos.DTO.Professor.ProfessorResponse;
import school.sptech.cursos.service.ProfessorService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/professores")
public class ProfessorController {

    private final ProfessorService service;

    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProfessorResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<ProfessorResponse> criar(@RequestBody @Valid ProfessorRequest request) {
        return ResponseEntity.status(201).body(service.salvar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ProfessorRequest request
    ) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
