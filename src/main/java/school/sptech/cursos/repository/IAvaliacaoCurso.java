package school.sptech.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.cursos.entity.AvaliacaoCurso;

import java.util.List;

public interface IAvaliacaoCurso extends JpaRepository<AvaliacaoCurso,Long> {
    boolean existsByCursoIdAndUsuarioId(Long cursoId, Long usuarioId);
    List<AvaliacaoCurso> findByCursoId(Long cursoId);
}
