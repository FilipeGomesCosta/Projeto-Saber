package bancodados;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

public class Email {

    private static String codigoVerificacao = null;
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final Pattern PATTERN = Pattern.compile(EMAIL_REGEX);

    public static String getCodigoVerificacao() {
        return codigoVerificacao;
    }

    private static void gerarNovoCodigo() {
        codigoVerificacao = String.valueOf(ThreadLocalRandom.current().nextInt(1000, 10000));
    }

    public static boolean validarEmail(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = PATTERN.matcher(email);
        return matcher.matches();
    }

    public static boolean mandarEmail(String emailDestinatario) {
        gerarNovoCodigo();
        String emailRemetente = "saber.noreply";
        String senhaRemetente = "rmxfuuzhpvzphrrf";

        Properties propiedades = new Properties();
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.port", "587");
        propiedades.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(propiedades, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailRemetente, senhaRemetente);
            }
        });

        String conteudoEmail = """
Prezado(a) estudante,
                          
Foi enviado a nós uma solicitação para a redefinição de sua senha. Caso tenha sido você, insira o seguinte código de verificação: 
\n""" + codigoVerificacao + """

\nSe não foi você que abriu a solicitação, ignore esta mensagem. Sua senha permanecerá a mesma.
                                            
Saudações acadêmicas,
Equipe Saber+
                          """;

        try {
            Message mensagem = new MimeMessage(session);
            mensagem.setFrom(new InternetAddress(emailRemetente));
            mensagem.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDestinatario));
            mensagem.setSubject("Redefinir Senha - Saber+");
            mensagem.setText(conteudoEmail);
            Transport.send(mensagem);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }
}
