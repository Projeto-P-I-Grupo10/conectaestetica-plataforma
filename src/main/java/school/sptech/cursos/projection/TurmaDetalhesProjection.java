package school.sptech.cursos.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface TurmaDetalhesProjection {
    Long getTurmaId();
    String getTurmaNome();
    Boolean getTurmaCursoAtivo();
    LocalDateTime getTurmaDataInicio();
    LocalDateTime getTurmaDataEncerramento();
    Integer getTurmaQtdVagas();
    BigDecimal getTurmaPreco();
    String getCursoNome();
    String getCursoDescricao();
    Long getCursoId();
    String getCursoImagem();
    String getProfessorNome();
    String getProfessorFoto();
    String getProfessorDescricao();
    String getProfessorRedesocial();
    String getAreaNome();
    String getEnderecoRua();
    String getEnderecoNumero();
    String getEnderecoCidade();
    Double getAvaliacaoCurso();
    Integer getAvaliacoesTotal();
}

