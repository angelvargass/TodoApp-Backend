package vargas.angel.todo.email;

import vargas.angel.todo.dto.User;

public class EmailTemplate {

    private EmailInformation emailInformation;

    public EmailTemplate() {
        this.emailInformation = new EmailInformation();
    }

    public EmailInformation newUser(User user) {
        emailInformation.setSubject("Welcome to ETask");
        emailInformation.setTo(user.getEmail());

        String emailBody;
        emailBody = "<p>" + user.getName() + " ,welcome to ETask</p>" +
                "<a href=\"https://localhost:4200/activate/" + user.getId() + "\">" + "Click here to activate your account<a/>";
        emailInformation.setMessage(emailBody);
        return emailInformation;
    }
}
