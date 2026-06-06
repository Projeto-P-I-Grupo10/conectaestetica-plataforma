package school.sptech.cursos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cursos.dto.matricula.AtualizarStatusMatriculaRequest;
import school.sptech.cursos.dto.matricula.MatriculaRequest;
import school.sptech.cursos.dto.matricula.MatriculaResponse;
import school.sptech.cursos.service.MatriculaService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    private final MatriculaService service;

    public MatriculaController(MatriculaService service) {
        this.service = service;
    }

    @Operation(summary = "Lista todos as matricula", description = "Retorna uma lista de matriculas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<MatriculaResponse>> listar() {

        List<MatriculaResponse> matriculas =
                service.listar();

        return ResponseEntity.status(200).body(matriculas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaResponse> buscarPorId(
            @PathVariable Long id) {

        MatriculaResponse matricula =
                service.buscarPorId(id);

        return ResponseEntity.status(200).body(matricula);
    }

    @Operation(summary = "Cria nova matricula", description = "Cadastra nova matricula com base nos dados enviados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Matricula criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<MatriculaResponse> criar(
            @Valid @RequestBody MatriculaRequest request) {

        MatriculaResponse matricula =
                service.criar(request);

        return ResponseEntity.status(201)
                .body(matricula);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatriculaResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody MatriculaRequest request) {

        MatriculaResponse matricula =
                service.atualizar(id, request);

        return ResponseEntity.status(200).body(matricula);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id) {

        service.deletar(id);

        return ResponseEntity.status(204).build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<MatriculaResponse> atualizarStatus(
            @PathVariable Long id,
            @RequestBody @Valid AtualizarStatusMatriculaRequest request) {

        return ResponseEntity.ok(
                service.atualizarStatus(id, request)
        );
    }
}
