package school.sptech.cursos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import school.sptech.cursos.dto.usuario.*;
import school.sptech.cursos.projection.usuario.UsuarioTurmarCompradasProjection;
import school.sptech.cursos.service.RecuperacaoSenhaService;
import school.sptech.cursos.service.UsuarioService;

import java.time.Duration;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    public static final String COOKIE_NOME = "authToken";

    @Value("${jwt.validity}")
    private long jwtValidity;

    private final UsuarioService service;
    private final RecuperacaoSenhaService recuperacaoSenhaService;

    public UsuarioController(UsuarioService service, RecuperacaoSenhaService recuperacaoSenhaService) {
        this.service = service;
        this.recuperacaoSenhaService = recuperacaoSenhaService;
    }

    @Operation(
            summary = "Lista todos os usuários",
            description = "Retorna uma lista de usuários cadastrados",
            security = {@SecurityRequirement(name = "bearerAuth")}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<Page<UsuarioResponse>> listar(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(service.listar(pageable));
    }

    @Operation(
            summary = "Cria um novo usuário",
            description = "Cadastra um usuário com base nos dados enviados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<UsuarioResponse> criar(
            @RequestBody @Valid UsuarioRequest request
    ) {
        return ResponseEntity.status(201).body(service.salvar(request));
    }

    @Operation(
            summary = "Busca usuário por ID",
            description = "Retorna um usuário específico pelo seu identificador",
            security = {@SecurityRequirement(name = "bearerAuth")}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.buscarId(id));
    }

    @Operation(
            summary = "Login de usuário",
            description = "Autentica o usuário e retorna token JWT"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    @PostMapping("/login")
    public ResponseEntity<UsuarioToken> login(
            @RequestBody UsuarioLoginRequest request,
            HttpServletResponse response
    ) {

        UsuarioToken autenticado =
                service.autenticar(request.getEmail(), request.getSenha());

        ResponseCookie cookie = ResponseCookie.from(
                        COOKIE_NOME,
                        autenticado.getToken()
                )
                .httpOnly(true)
                .secure(false)
                .sameSite("Strict")
                .path("/")
                .maxAge(Duration.ofSeconds(jwtValidity))
                .build();

        response.addHeader(
                HttpHeaders.SET_COOKIE,
                cookie.toString()
        );

        UsuarioToken sessao = new UsuarioToken();

        sessao.setId(autenticado.getId());
        sessao.setNome(autenticado.getNome());
        sessao.setEmail(autenticado.getEmail());
        sessao.setToken(autenticado.getToken());

        return ResponseEntity.ok(sessao);
    }

    @Operation(
            summary = "Logout de usuário",
            description = "Remove o cookie de autenticação",
            security = {@SecurityRequirement(name = "bearerAuth")}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Logout realizado com sucesso"
            )
    })
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            HttpServletResponse response
    ) {

        ResponseCookie cookie = ResponseCookie.from(
                        COOKIE_NOME,
                        ""
                )
                .httpOnly(true)
                .secure(false)
                .sameSite("Strict")
                .path("/")
                .maxAge(0)
                .build();

        response.addHeader(
                HttpHeaders.SET_COOKIE,
                cookie.toString()
        );

        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Atualiza usuário",
            description = "Atualiza os dados de um usuário existente",
            security = {@SecurityRequirement(name = "bearerAuth")}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuário atualizado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuário não encontrado"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid UsuarioAtualizarRequest request
    ) {
        return ResponseEntity.ok(
                service.atualizar(id, request)
        );
    }

    @Operation(
            summary = "Deleta usuário",
            description = "Remove um usuário pelo seu ID",
            security = {@SecurityRequirement(name = "bearerAuth")}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Usuário deletado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuário não encontrado"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id
    ) {
        service.deletarPorID(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/verificar-senha")
    public ResponseEntity<ResetSenhaResponse> verificarSenha(
            @PathVariable Long id,
            @RequestBody ResetSenhaRequest request
    ) {

        if (!service.verificarSenhaUsuario(
                id,
                request.getSenhaAtual()
        )) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(
                            new ResetSenhaResponse(
                                    "Senha atual incorreta",
                                    HttpStatus.UNAUTHORIZED
                            )
                    );
        }

        return ResponseEntity.ok(
                new ResetSenhaResponse(
                        "Senha válida",
                        HttpStatus.OK
                )
        );
    }

    @Operation(
            summary = "Atualiza senha do usuário",
            description = "Permite que o usuário altere apenas a própria senha",
            security = {@SecurityRequirement(name = "bearerAuth")}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Senha atualizada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Usuário sem permissão para alterar esta senha"
            )
    })
    @PatchMapping("/{id}/senha")
    public ResponseEntity<ResetSenhaResponse> atualizarSenha(
            @PathVariable Long id,
            @RequestBody ResetSenhaNovaRequest request,
            Authentication authentication
    ) {

        String emailUsuarioLogado = authentication.getName();

        UsuarioResponse usuarioLogado =
                service.buscarPorEmail(emailUsuarioLogado);

        if (!usuarioLogado.getId().equals(id)) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(
                            new ResetSenhaResponse(
                                    "Você não tem permissão para alterar esta senha",
                                    HttpStatus.FORBIDDEN
                            )
                    );
        }

        service.atualizarSenhaUsuario(
                id,
                request.getNovaSenha()
        );

        return ResponseEntity.ok(
                new ResetSenhaResponse(
                        "Senha atualizada com sucesso",
                        HttpStatus.OK
                )
        );
    }

    @Operation(
            summary = "Esqueci minha senha",
            description = "Envia por e-mail um link para redefinir a senha. Sempre responde 200, exista ou não o e-mail."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitação processada")
    })
    @PostMapping("/esqueci-senha")
    public ResponseEntity<ResetSenhaResponse> esqueciSenha(
            @RequestBody @Valid EsqueciSenhaRequest request
    ) {
        recuperacaoSenhaService.solicitarRecuperacao(request.getEmail());

        return ResponseEntity.ok(
                new ResetSenhaResponse(
                        "Se o e-mail estiver cadastrado, você receberá as instruções para redefinir a senha",
                        HttpStatus.OK
                )
        );
    }

    @Operation(
            summary = "Redefinir senha com token",
            description = "Define a nova senha usando o token recebido por e-mail"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Senha redefinida com sucesso"),
            @ApiResponse(responseCode = "400", description = "Token inválido/expirado ou senha inválida")
    })
    @PostMapping("/redefinir-senha")
    public ResponseEntity<ResetSenhaResponse> redefinirSenha(
            @RequestBody @Valid RedefinirSenhaTokenRequest request
    ) {
        recuperacaoSenhaService.redefinirSenha(request.getToken(), request.getNovaSenha());

        return ResponseEntity.ok(
                new ResetSenhaResponse("Senha redefinida com sucesso", HttpStatus.OK)
        );
    }

    @GetMapping("/{id}/turmas-compradas-por-usuario")
    public ResponseEntity<List<UsuarioTurmarCompradasProjection>>
    turmasCompradasPorUsuario(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                service.turmasCompradasPorUsuario(id)
        );
    }
}