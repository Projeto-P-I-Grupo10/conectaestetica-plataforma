package school.sptech.cursos.dto.usuario;

import org.springframework.http.HttpStatus;

public class ResetSenhaResponse {
    private String mensagem;
    private HttpStatus status;

    public ResetSenhaResponse(String mensagem, HttpStatus status) {
        this.mensagem = mensagem;
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
