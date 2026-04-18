package school.sptech.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.cursos.model.Professor;

import java.util.Optional;

public interface IProfessorRepository extends JpaRepository<Professor,Long> {
    boolean existsByEmail(String email);
    Optional<Professor> findByEmail(String email);
}
