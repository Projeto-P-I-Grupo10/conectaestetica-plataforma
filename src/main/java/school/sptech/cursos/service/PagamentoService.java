package school.sptech.cursos.service;

import org.springframework.stereotype.Service;
import school.sptech.cursos.dto.pagamento.PagamentoCriadoMessage;
import school.sptech.cursos.dto.pagamento.PagamentoRequest;
import school.sptech.cursos.messaging.PagamentoPublisher;

@Service
public class PagamentoService {

    private final PagamentoPublisher pagamentoPublisher;

    public PagamentoService(PagamentoPublisher pagamentoPublisher) {
        this.pagamentoPublisher = pagamentoPublisher;
    }

    public void criarPagamento(PagamentoRequest request) {
        PagamentoCriadoMessage mensagem = new PagamentoCriadoMessage(
                request.getIdTurma(),
                request.getIdUsuario(),
                request.getEmail(),
                request.getMetodoPagamento(),
                request.getStatus(),
                request.getValor()
        );

        pagamentoPublisher.publicarPagamentoCriado(mensagem);
    }
}
