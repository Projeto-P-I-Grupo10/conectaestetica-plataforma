package school.sptech.cursos.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cursos.dto.enderecoCurso.EnderecoCursoRequest;
import school.sptech.cursos.dto.enderecoCurso.EnderecoCursoResponse;
import school.sptech.cursos.service.EnderecoCursoService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/enderecos-curso")
public class EnderecoCursoController {

    private final EnderecoCursoService service;

    public EnderecoCursoController(EnderecoCursoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EnderecoCursoResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<EnderecoCursoResponse> criar(@RequestBody @Valid EnderecoCursoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoCursoResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid EnderecoCursoRequest request
    ) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoCursoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}
