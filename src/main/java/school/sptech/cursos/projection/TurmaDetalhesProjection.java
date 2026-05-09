package school.sptech.cursos.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface TurmaDetalhesProjection {
    String getTurmaNome();
    Boolean getTurmaCursoAtivo();
    LocalDateTime getTurmaDataInicio();
    LocalDateTime getTurmaDataEncerramento();
    Integer getTurmaQtdVagas();
    BigDecimal getTurmaPreco();
    String getCursoNome();
    String getCursoDescricao();
    String getCursoImagem();
    String getProfessorNome();
    String getProfessorFoto();
    String getProfessorDescricao();
    String getProfessorRedesocial();
    String getAreaNome();
}

