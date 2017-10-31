package com.crescendo.app.mailing;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/** mailing setup
 * @author Groep 5
 * @version 0.1
 */
public class Mailing {

    private JavaMailSender mailSender;
    private SimpleMailMessage simpleMailMessage;

    public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
        this.simpleMailMessage = simpleMailMessage;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMailWithattachment(String subject, String content, String mail, FileSystemResource file) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        simpleMailMessage.setText(content);
        simpleMailMessage.setSubject(subject);

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(simpleMailMessage.getFrom());
            helper.setTo(mail);
            helper.setSubject(simpleMailMessage.getSubject());
            helper.setText(simpleMailMessage.getText(), true);
            helper.setReplyTo("cvocrescendoreceiver@gmail.com");

            if (file.exists()) {
                helper.addAttachment(file.getFilename(), file);
            }

        } catch (MessagingException e) {
            throw new MailParseException(e);
        }
        mailSender.send(message);
    }
    public void sendMail(String subject, String content, String mail) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        simpleMailMessage.setText(content);
        simpleMailMessage.setSubject(subject);

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(simpleMailMessage.getFrom());
            helper.setTo(mail);
            helper.setSubject(simpleMailMessage.getSubject());
            helper.setText(simpleMailMessage.getText(), true);
            helper.setReplyTo("cvocrescendoreceiver@gmail.com");

        } catch (MessagingException e) {
            throw new MailParseException(e);
        }
        mailSender.send(message);
    }

}




