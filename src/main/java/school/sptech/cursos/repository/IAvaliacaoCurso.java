package school.sptech.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.cursos.entity.AvaliacaoCurso;

public interface IAvaliacaoCurso extends JpaRepository<AvaliacaoCurso,Long> {
    boolean existsByCursoIdAndUsuarioId(Long cursoId, Long usuarioId);
}
