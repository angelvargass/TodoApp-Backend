package vargas.angel.todo.email;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.stereotype.Component;

@Component
public class EmailManager {

    private final EmailProperties emailProperties;

    public EmailManager(EmailProperties emailProperties) {
        this.emailProperties = emailProperties;
    }

    public void sendEmail(EmailInformation emailInformation) {
        Email email = EmailBuilder.startingBlank()
                .from("Etask", emailProperties.getUsername())
                .to(emailInformation.getTo())
                .withSubject(emailInformation.getSubject())
                .withHTMLText(emailInformation.getMessage())
                .buildEmail();

        if(emailInformation.getAttachment() != null) {
            email.getAttachments().add(emailInformation.getAttachment());
        }

        buildMailer(email);
    }

    public void buildMailer(Email email) {
        Mailer mailer = MailerBuilder
                .withSMTPServer(emailProperties.getHost(), emailProperties.getSmtpport(),
                        emailProperties.getUsername(), emailProperties.getPassword())
                .async()
                .withThreadPoolSize(20)
                .buildMailer();

        mailer.sendMail(email);
    }
}
