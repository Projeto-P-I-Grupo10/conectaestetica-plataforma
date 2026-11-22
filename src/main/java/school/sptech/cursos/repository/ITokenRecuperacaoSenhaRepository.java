package school.sptech.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import school.sptech.cursos.entity.TokenRecuperacaoSenha;

import java.util.Optional;

@Repository
public interface ITokenRecuperacaoSenhaRepository extends JpaRepository<TokenRecuperacaoSenha, Long> {

    Optional<TokenRecuperacaoSenha> findByTokenHash(String tokenHash);

    @Modifying
    @Query("DELETE FROM TokenRecuperacaoSenha t WHERE t.usuario.id = :usuarioId")
    void deleteByUsuarioId(@Param("usuarioId") Long usuarioId);
}
