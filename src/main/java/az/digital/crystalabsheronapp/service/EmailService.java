package az.digital.crystalabsheronapp.service;

import az.digital.crystalabsheronapp.dao.entity.Mail;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender javaMailSender;

    public void sendMail(Mail mail) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("huseynliss18@gmail.com");
        message.setTo(mail.getToMail());
        message.setText(mail.getBody());
        message.setSubject(mail.getSubject());

        javaMailSender.send(message);
        System.out.println("Mail Sent Successfully...");

    }
}
