package vargas.angel.todo.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailProperties {

    @Value("${mail.host}")
    private String mailHost;

    @Value("${mail.port}")
    private String mailPort;

    @Value("${mail.username}")
    private String mailUserName;

    @Value("${mail.password}")
    private String mailPassword;

    public String getHost() {
        return mailHost;
    }

    public int getSmtpport() {
        return Integer.parseInt(mailPort);
    }

    public String getUsername() {
        return mailUserName;
    }

    public String getPassword() {
        return mailPassword;
    }
}
