package school.sptech.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.cursos.model.AreaCurso;

import java.util.Optional;

@Repository
public interface IAreaCursoRepository extends JpaRepository<AreaCurso,Long> {
    Optional<AreaCurso> findByNome(String nome);
}
