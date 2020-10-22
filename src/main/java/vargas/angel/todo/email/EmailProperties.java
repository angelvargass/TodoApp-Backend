package vargas.angel.todo.email;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class EmailProperties {

    private static final Properties properties = new Properties();
    private static final InputStream propertiesFile =
            EmailProperties.class.getClassLoader().getResourceAsStream(
                    "application.properties");

    public EmailProperties() {
        try {
            properties.load(propertiesFile);
        } catch (IOException e) {

        }
    }

    public String getHost() {
        return properties.getProperty("mail.host");
    }

    public int getSmtpport() {
        return Integer.parseInt(properties.getProperty("mail.port"));
    }

    public String getUsername() {
        return properties.getProperty("mail.username");
    }

    public String getPassword() {
        return properties.getProperty("mail.password");
    }
}
