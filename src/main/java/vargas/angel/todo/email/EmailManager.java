package vargas.angel.todo.email;

import org.apache.commons.mail.*;

public class EmailManager {

    private static final EmailProperties emailProperties = new EmailProperties();

    private final MultiPartEmail email;

    public EmailManager() throws EmailException {
        email = new MultiPartEmail();
        email.setSSLOnConnect(true);
        email.setHostName(emailProperties.getHost());
        email.setSmtpPort(emailProperties.getSmtpport());
        email.setAuthentication(emailProperties.getUsername(), emailProperties.getPassword());
        email.setFrom(emailProperties.getUsername());
    }

    public void sendEmail(EmailInformation emailInformation) throws EmailException {
        email.addTo(emailInformation.getTo());
        email.setSubject(emailInformation.getSubject());
        email.setMsg(emailInformation.getMessage());

        if(emailInformation.getAttachment() != null) {
            email.attach(emailInformation.getAttachment());
        }

        email.send();
    }
}
