package africa.semicolon.registrationandlogin.email;


import africa.semicolon.registrationandlogin.exceptions.AppUserException;
import lombok.AllArgsConstructor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
//@AllArgsConstructor

public class EmailService implements EmailSender{
    @Autowired
    private JavaMailSender mailSender;
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Override
    @Async
    public void send(String to, String email) throws AppUserException {
    try{
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(email, true);
        helper.setTo(to);
        helper.setSubject("confirm your email");
        helper.setFrom("ismailoluwayemisi@gmail.com");
        mailSender.send(mimeMessage);

    }catch (MessagingException e){
        LOGGER.error("failed to send email",e);
        throw new  AppUserException("failed to send email",404);
    }
    }
}
