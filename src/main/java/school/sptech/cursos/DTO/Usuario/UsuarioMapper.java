package school.sptech.cursos.DTO.Usuario;

import school.sptech.cursos.model.Usuario;

public class UsuarioMapper {

    public static Usuario of(UsuarioRequest request) {
        Usuario usuario = new Usuario();

        usuario.setEmail(request.getEmail());
        usuario.setNome(request.getNome());
        usuario.setSenha(request.getSenha());

        return usuario;
    }

    public static Usuario of(UsuarioLoginRequest request) {
        Usuario usuario = new Usuario();

        usuario.setEmail(request.getEmail());
        usuario.setSenha(request.getSenha());

        return usuario;
    }

    public static UsuarioToken of(Usuario usuario, String token) {
        UsuarioToken usuarioTokenDto = new UsuarioToken();

        usuarioTokenDto.setId(usuario.getId());
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setToken(token);

        return usuarioTokenDto;
    }


    public static UsuarioSessaoDto ofSessao(UsuarioToken tokenDto) {
        UsuarioSessaoDto dto = new UsuarioSessaoDto();

        dto.setId(tokenDto.getId());
        dto.setEmail(tokenDto.getEmail());
        dto.setNome(tokenDto.getNome());

        return dto;
    }

}