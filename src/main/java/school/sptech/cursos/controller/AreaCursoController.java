package school.sptech.cursos.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cursos.DTO.Area.AreaCursoRequest;
import school.sptech.cursos.DTO.Area.AreaCursoResponse;
import school.sptech.cursos.service.AreaCursoService;

import java.util.List;

@RestController
@RequestMapping("/areaCursos")
@CrossOrigin(origins = "*")
public class AreaCursoController {

    public final AreaCursoService service;

    public AreaCursoController(AreaCursoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AreaCursoResponse>> listarAreasCursos()
    {
        List<AreaCursoResponse> areas = this.service.listarAreasCurso();
        if(areas.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(areas);
    }

    @PostMapping
    public ResponseEntity<AreaCursoResponse> criarAreaCurso(@RequestBody @Valid AreaCursoRequest request)
    {
        AreaCursoResponse area = this.service.adicionarAreaCurso(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(area);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AreaCursoResponse> atualizarAreaCurso(@RequestBody @Valid AreaCursoRequest request,@PathVariable Long id)
    {
        AreaCursoResponse area = this.service.atualizarAreaCurso(request,id);
        return ResponseEntity.status(HttpStatus.OK).body(area);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarAreaCurso(@PathVariable Long id)
    {
        this.service.removerAreaCurso(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
