package school.sptech.cursos.dto.dashboard;

import java.math.BigDecimal;

public class DashLucroSemanaResponse {

    BigDecimal lucroSemanaPassada;
    BigDecimal lucroSemanaAtual;

    public DashLucroSemanaResponse(BigDecimal lucroSemanaPassada, BigDecimal lucroSemanaAtual) {
        this.lucroSemanaPassada = lucroSemanaPassada;
        this.lucroSemanaAtual = lucroSemanaAtual;
    }

    public BigDecimal getLucroSemanaPassada() {
        return lucroSemanaPassada;
    }

    public void setLucroSemanaPassada(BigDecimal lucroSemanaPassada) {
        this.lucroSemanaPassada = lucroSemanaPassada;
    }

    public BigDecimal getLucroSemanaAtual() {
        return lucroSemanaAtual;
    }

    public void setLucroSemanaAtual(BigDecimal lucroSemanaAtual) {
        this.lucroSemanaAtual = lucroSemanaAtual;
    }
}
