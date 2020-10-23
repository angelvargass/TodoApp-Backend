package vargas.angel.todo.email;

import org.apache.commons.mail.*;
import org.springframework.stereotype.Component;

@Component
public class EmailManager {

    private static final EmailProperties emailProperties = new EmailProperties();

    private final HtmlEmail email;

    public EmailManager() throws EmailException {
        email = new HtmlEmail();
        email.setSSLOnConnect(true);
        email.setHostName(emailProperties.getHost());
        email.setSmtpPort(emailProperties.getSmtpport());
        email.setAuthentication(emailProperties.getUsername(), emailProperties.getPassword());
        email.setFrom(emailProperties.getUsername());
    }

    public void sendEmail(EmailInformation emailInformation) throws EmailException {
        email.addTo(emailInformation.getTo());
        email.setSubject(emailInformation.getSubject());
        email.setHtmlMsg(emailInformation.getMessage());

        if(emailInformation.getAttachment() != null) {
            email.attach(emailInformation.getAttachment());
        }

        email.send();
    }
}
