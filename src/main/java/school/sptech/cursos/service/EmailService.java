
package school.sptech.cursos.service;

import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender mailSender;

    @Value("${app.mail.from}")
    private String remetente;

    @Value("${app.mail.nome-remetente}")
    private String nomeRemetente;

    @Value("${app.frontend-url}")
    private String frontendUrl;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Roda em outra thread (@Async): a requisição não fica esperando o SMTP responder
     * e o tempo de resposta não revela se o e-mail existe ou não no sistema.
     */
    @Async
    public void enviarRecuperacaoSenha(String destinatario, String nome, String link, long minutosValidade) {
        try {
            MimeMessage mensagem = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensagem, true, "UTF-8");

            helper.setFrom(remetente, nomeRemetente);
            helper.setTo(destinatario);
            helper.setSubject("Redefinição de senha - Conecta Estética");

            String nomeSeguro = HtmlUtils.htmlEscape(nome == null ? "" : nome);

            String texto = "Olá, " + nome + "!\n\n"
                    + "Recebemos um pedido para redefinir sua senha. Acesse o link abaixo (válido por "
                    + minutosValidade + " minutos):\n" + link + "\n\n"
                    + "Se você não fez esse pedido, ignore este e-mail: sua senha continua a mesma.";

            String html = """
                    <div style="font-family:Arial,sans-serif;max-width:520px;margin:auto;color:#333">
                      <h2>Redefinição de senha</h2>
                      <p>Olá, <strong>%s</strong>!</p>
                      <p>Recebemos um pedido para redefinir a senha da sua conta.
                         O link abaixo é válido por <strong>%d minutos</strong> e pode ser usado uma única vez.</p>
                      <p style="text-align:center;margin:32px 0">
                        <a href="%s" style="background:#7b2cbf;color:#fff;padding:12px 24px;
                           border-radius:6px;text-decoration:none;font-weight:bold">Redefinir minha senha</a>
                      </p>
                      <p style="font-size:13px;color:#777">Se o botão não funcionar, copie e cole este endereço no navegador:<br>%s</p>
                      <p style="font-size:13px;color:#777">Se você não fez esse pedido, ignore este e-mail: sua senha continua a mesma.</p>
                    </div>
                    """.formatted(nomeSeguro, minutosValidade, link, link);

            helper.setText(texto, html);

            mailSender.send(mensagem);
            LOGGER.info("[EMAIL] E-mail de recuperação de senha enviado");
        } catch (Exception e) {
            LOGGER.error("[EMAIL] Falha ao enviar e-mail de recuperação de senha: {}", e.getMessage(), e);
        }
    }

    @Async
    public void enviarBoasVindas(String destinatario, String nome) {
        String nomeLimpo = nome == null ? "" : nome;
        String nomeSeguro = HtmlUtils.htmlEscape(nomeLimpo);
        String linkLogin = frontendUrl + "/login";

        String texto = "Olá, " + nomeLimpo + "!\n\n"
                + "Sua conta na Conecta Estética foi criada com sucesso. Para acessar, entre por aqui:\n"
                + linkLogin + "\n\n"
                + "Se você não criou essa conta, entre em contato com a gente.";

        String html = """
                <div style="font-family:Arial,sans-serif;max-width:520px;margin:auto;color:#333">
                  <h2>Bem-vindo(a) à Conecta Estética!</h2>
                  <p>Olá, <strong>%s</strong>!</p>
                  <p>Sua conta foi criada com sucesso. Agora você já pode conhecer nossos cursos e se matricular.</p>
                  <p style="text-align:center;margin:32px 0">
                    <a href="%s" style="background:#c9a46c;color:#fff;padding:12px 24px;
                       border-radius:6px;text-decoration:none;font-weight:bold">Acessar minha conta</a>
                  </p>
                  <p style="font-size:13px;color:#777">Se você não criou essa conta, entre em contato com a gente.</p>
                </div>
                """.formatted(nomeSeguro, linkLogin);

        enviarEmail(destinatario, "Bem-vindo(a) à Conecta Estética", texto, html, "boas-vindas");
    }

    @Async
    public void enviarSenhaAlterada(String destinatario, String nome) {
        String nomeLimpo = nome == null ? "" : nome;
        String nomeSeguro = HtmlUtils.htmlEscape(nomeLimpo);

        String quando = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"))
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm"));

        String linkRecuperar = frontendUrl + "/esqueci-senha";

        String texto = "Olá, " + nomeLimpo + "!\n\n"
                + "A senha da sua conta foi alterada em " + quando + ".\n\n"
                + "Se foi você, não precisa fazer nada.\n"
                + "Se NÃO foi você, redefina sua senha agora: " + linkRecuperar;

        String html = """
                <div style="font-family:Arial,sans-serif;max-width:520px;margin:auto;color:#333">
                  <h2>Sua senha foi alterada</h2>
                  <p>Olá, <strong>%s</strong>!</p>
                  <p>A senha da sua conta foi alterada em <strong>%s</strong>.</p>
                  <p>Se foi você, não precisa fazer nada.</p>
                  <p style="background:#fff6e5;border-left:4px solid #c9a46c;padding:12px">
                    <strong>Não foi você?</strong> Redefina sua senha agora para proteger sua conta:
                    <br><a href="%s">Redefinir minha senha</a>
                  </p>
                </div>
                """.formatted(nomeSeguro, quando, linkRecuperar);

        enviarEmail(destinatario, "Sua senha foi alterada - Conecta Estética", texto, html, "senha-alterada");
    }

    private void enviarEmail(String destinatario, String assunto, String texto, String html, String tipo) {
        try {
            MimeMessage mensagem = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensagem, true, "UTF-8");

            helper.setFrom(remetente, nomeRemetente);
            helper.setTo(destinatario);
            helper.setSubject(assunto);
            helper.setText(texto, html);

            mailSender.send(mensagem);
            LOGGER.info("[EMAIL] E-mail de {} enviado", tipo);
        } catch (Exception e) {
            LOGGER.error("[EMAIL] Falha ao enviar e-mail de {}: {}", tipo, e.getMessage(), e);
        }
    }
}
