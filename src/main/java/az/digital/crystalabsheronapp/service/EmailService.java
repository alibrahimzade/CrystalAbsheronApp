package az.digital.crystalabsheronapp.service;

import az.digital.crystalabsheronapp.dao.entity.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender javaMailSender;

    public void sendMail(String to,String subject ,String body ) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ibragimali56@gmail.com");
//        message.setTo(mail.getToMail());
//        message.setText(mail.getBody());
//        message.setSubject(mail.getSubject());

        message.setTo(to);
        message.setText(subject);
        message.setSubject(body);

        javaMailSender.send(message);
        log.info("Mail Sent Successfully...");

    }

}
