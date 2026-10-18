package school.sptech.cursos.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.cursos.dto.pagamento.PagamentoRequest;
import school.sptech.cursos.service.PagamentoService;

@RestController
@RequestMapping("/pagamentos")
@CrossOrigin(origins = "*")
public class PagamentoController {

    private final PagamentoService service;

    public PagamentoController(PagamentoService service) {
        this.service = service;
    }

    /**
     * Recebe os dados de pagamento do frontend e publica o evento
     * {@code pagamento.criado} no RabbitMQ. O processamento é assíncrono,
     * por isso responde {@code 202 Accepted}.
     */
    @PostMapping
    public ResponseEntity<Void> criarPagamento(@RequestBody @Valid PagamentoRequest request) {
        service.criarPagamento(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
