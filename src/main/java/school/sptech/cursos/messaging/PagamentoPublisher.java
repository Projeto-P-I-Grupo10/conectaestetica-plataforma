package school.sptech.cursos.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import school.sptech.cursos.dto.pagamento.PagamentoCriadoMessage;

@Component
public class PagamentoPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(PagamentoPublisher.class);

    private final RabbitTemplate rabbitTemplate;

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Value("${app.rabbitmq.routingkey.pagamento-criado}")
    private String pagamentoCriadoRoutingKey;

    public PagamentoPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Publica o evento {@code pagamento.criado} na exchange de pagamentos.
     *
     * @param mensagem payload com os dados do pagamento a ser criado
     */
    public void publicarPagamentoCriado(PagamentoCriadoMessage mensagem) {
        rabbitTemplate.convertAndSend(exchange, pagamentoCriadoRoutingKey, mensagem);
        LOGGER.info("[PAGAMENTO] Evento '{}' publicado na exchange '{}' para o usuário {} / turma {}",
                pagamentoCriadoRoutingKey, exchange, mensagem.idUsuario(), mensagem.idTurma());
    }
}
