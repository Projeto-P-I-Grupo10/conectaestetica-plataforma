package school.sptech.cursos.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cursos.dto.historicoEnderecoUsuario.HistoricoEnderecoUsuarioCreateRequest;
import school.sptech.cursos.dto.historicoEnderecoUsuario.HistoricoEnderecoUsuarioResponse;
import school.sptech.cursos.service.HistoricoEnderecoUsuarioService;

import java.util.List;

@RestController
@RequestMapping("/historicos-endereco")
@CrossOrigin(origins = "*")
public class HistoricoEnderecoUsuarioController {

    private final HistoricoEnderecoUsuarioService service;

    public HistoricoEnderecoUsuarioController(HistoricoEnderecoUsuarioService service) {
        this.service = service;
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<HistoricoEnderecoUsuarioResponse>> listarPorUsuario(@PathVariable Long usuarioId) {
        List<HistoricoEnderecoUsuarioResponse> enderecos = this.service.listarPorUsuario(usuarioId);
        if (enderecos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(enderecos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoricoEnderecoUsuarioResponse> buscarPorId(@PathVariable Long id) {
        HistoricoEnderecoUsuarioResponse endereco = this.service.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(endereco);
    }

    @PostMapping
    public ResponseEntity<HistoricoEnderecoUsuarioResponse> criarEndereco(
            @RequestBody @Valid HistoricoEnderecoUsuarioCreateRequest request) {
        HistoricoEnderecoUsuarioResponse endereco = this.service.salvar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(endereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoricoEnderecoUsuarioResponse> atualizarEndereco(
            @RequestBody @Valid HistoricoEnderecoUsuarioCreateRequest request,
            @PathVariable Long id) {
        HistoricoEnderecoUsuarioResponse endereco = this.service.atualizar(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(endereco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarEndereco(@PathVariable Long id) {
        this.service.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{enderecoId}/selecionar")
    public ResponseEntity<HistoricoEnderecoUsuarioResponse> selecionarEnderecoAtual(
            @PathVariable Long enderecoId) {
        HistoricoEnderecoUsuarioResponse endereco = this.service.selecionarEnderecoAtual(enderecoId);
        return ResponseEntity.status(HttpStatus.OK).body(endereco);
    }

}
