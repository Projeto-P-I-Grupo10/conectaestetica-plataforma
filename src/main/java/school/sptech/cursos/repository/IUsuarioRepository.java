package school.sptech.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import school.sptech.cursos.entity.Usuario;
import school.sptech.cursos.projection.TurmaDetalhesProjection;
import school.sptech.cursos.projection.usuario.UsuarioTurmarCompradasProjection;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);
    boolean existsByTelefone(String telefone);
    Optional<Usuario> findByEmailAndSenha(String email, String senha);
    Optional<Usuario> findByEmail(String email);

    @Query(value = """
        SELECT
         c.nome AS nomeCurso,
         c.imagem AS imagemCurso,
         c.descricao AS descricaoCurso
         FROM pagamento p
        JOIN usuario u ON p.id_usuario = u.id
        JOIN turma t ON p.id_turma = t.id
        JOIN curso c ON t.curso_id = c.id
        WHERE u.id = :id AND p.status = "approved";
    """, nativeQuery = true)
    List<UsuarioTurmarCompradasProjection> buscarTurmasCompradasPorUsuario(@Param("id") Long id);
}

