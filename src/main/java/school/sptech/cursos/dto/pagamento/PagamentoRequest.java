package school.sptech.cursos.dto.pagamento;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class PagamentoRequest {

    @NotNull(message = "idTurma é obrigatório")
    private Long idTurma;

    @NotNull(message = "idUsuario é obrigatório")
    private Long idUsuario;

    @NotBlank(message = "email é obrigatório")
    @Email(message = "email inválido")
    private String email;

    @NotBlank(message = "metodoPagamento é obrigatório")
    private String metodoPagamento;

    @NotBlank(message = "status é obrigatório")
    private String status;

    @NotNull(message = "valor é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "valor deve ser maior que zero")
    private BigDecimal valor;

    public Long getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Long idTurma) {
        this.idTurma = idTurma;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
