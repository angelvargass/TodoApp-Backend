package vargas.angel.todo.email;

import org.simplejavamail.api.email.AttachmentResource;

public class EmailInformation {

    private String subject;
    private String to;
    private AttachmentResource attachment;
    private String message;

    public EmailInformation() {
    }

    public EmailInformation(String subject, String to, String message) {
        this.subject = subject;
        this.to = to;
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public AttachmentResource getAttachment() {
        return attachment;
    }

    public void setAttachment(AttachmentResource attachment) {
        this.attachment = attachment;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
