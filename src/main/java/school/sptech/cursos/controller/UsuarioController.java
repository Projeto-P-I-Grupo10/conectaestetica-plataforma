package school.sptech.cursos.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cursos.DTO.Usuario.UsuarioRequest;
import school.sptech.cursos.DTO.Usuario.UsuarioResponse;
import school.sptech.cursos.service.UsuarioService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> criar(@RequestBody @Valid UsuarioRequest request) {
        return ResponseEntity.status(201).body(service.salvar(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarId(id));
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponse> login(
            @RequestBody UsuarioRequest request
    ) {
        return ResponseEntity.ok(
                service.buscarPorEmailESenha(request.getEmail(), request.getSenha())
        );
    }


    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid UsuarioRequest request
    ) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletarPorID(id);
        return ResponseEntity.noContent().build();
    }
}
