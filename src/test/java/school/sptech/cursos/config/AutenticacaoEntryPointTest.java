package school.sptech.cursos.config;

import org.junit.jupiter.api.Test;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("AutenticacaoEntryPoint")
@ExtendWith(MockitoExtension.class)
class AutenticacaoEntryPointTest {

    @InjectMocks
    private AutenticacaoEntryPoint autenticacaoEntryPoint;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Test
    @DisplayName("Deve retornar 401 e mensagem de token inválido quando ocorrer BadCredentialsException")
    void deveRetornar401QuandoBadCredentialsException() throws IOException {

        // Arrange
        AuthenticationException exception =
                new BadCredentialsException("Credenciais inválidas");

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(printWriter);

        // Act
        autenticacaoEntryPoint.commence(request, response, exception);

        // Assert
        printWriter.flush();

        verify(response).setContentType("application/json");
        verify(response).setCharacterEncoding("UTF-8");
        verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        org.junit.jupiter.api.Assertions.assertEquals(
                "{\"error\":\"Token JWT inválido ou ausente\"}",
                stringWriter.toString()
        );
    }

    @Test
    @DisplayName("Deve retornar 401 e mensagem de token inválido quando ocorrer InsufficientAuthenticationException")
    void deveRetornar401QuandoInsufficientAuthenticationException() throws IOException {

        // Arrange
        AuthenticationException exception =
                new InsufficientAuthenticationException("Token ausente");

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(printWriter);

        // Act
        autenticacaoEntryPoint.commence(request, response, exception);

        // Assert
        printWriter.flush();

        verify(response).setContentType("application/json");
        verify(response).setCharacterEncoding("UTF-8");
        verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        org.junit.jupiter.api.Assertions.assertEquals(
                "{\"error\":\"Token JWT inválido ou ausente\"}",
                stringWriter.toString()
        );
    }

    @Test
    @DisplayName("Deve retornar 403 e mensagem de acesso negado para outras AuthenticationException")
    void deveRetornar403QuandoOutraAuthenticationException() throws IOException {

        // Arrange
        AuthenticationException exception = new AuthenticationException("Acesso negado") {};

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(printWriter);

        // Act
        autenticacaoEntryPoint.commence(request, response, exception);

        // Assert
        printWriter.flush();

        verify(response).setContentType("application/json");
        verify(response).setCharacterEncoding("UTF-8");
        verify(response).setStatus(HttpServletResponse.SC_FORBIDDEN);

        org.junit.jupiter.api.Assertions.assertEquals(
                "{\"error\":\"Acesso negado: permissões insuficientes\"}",
                stringWriter.toString()
        );
    }
}