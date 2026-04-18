package school.sptech.cursos.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cursos.DTO.Curso.CursoRequest;
import school.sptech.cursos.DTO.Curso.CursoResponse;
import school.sptech.cursos.service.CursoService;

import java.util.List;

@RestController
@RequestMapping("/cursos")
@CrossOrigin(origins = "*")
public class CursoController {

    public final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<List<CursoResponse>> listarCursos()
    {
        List<CursoResponse> cursos = this.service.listarCursos();
        if(cursos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

        @PostMapping
    public ResponseEntity<CursoResponse> criarCurso(@RequestBody @Valid CursoRequest request) {
        CursoResponse curso = this.service.adicionarCurso(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoResponse> atualizarAreaCurso(@RequestBody @Valid CursoRequest request,@PathVariable Long id)
    {
        CursoResponse area = this.service.atualizarCurso(request,id);
        return ResponseEntity.status(HttpStatus.OK).body(area);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarCurso(@PathVariable Long id)
    {
        this.service.removerCurso(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
