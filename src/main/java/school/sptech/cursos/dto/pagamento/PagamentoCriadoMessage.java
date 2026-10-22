package school.sptech.cursos.dto.pagamento;

import java.math.BigDecimal;

public record PagamentoCriadoMessage(
        Long idTurma,
        Long idUsuario,
        String email,
        String metodoPagamento,
        String status,
        BigDecimal valor
) {
}
