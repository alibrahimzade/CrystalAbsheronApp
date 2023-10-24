package az.digital.crystalabsheronapp.service;

import az.digital.crystalabsheronapp.dao.entity.CustomerInfo;
import az.digital.crystalabsheronapp.dao.entity.Mail;
import az.digital.crystalabsheronapp.dto.CustomerInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

import static az.digital.crystalabsheronapp.enums.Payments.IS_LATE;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender javaMailSender;

    public void sendMail(CustomerInfoDto customerInfoDto, String subject, String text) {
        if (customerInfoDto.getStatus() == IS_LATE) {
            try {
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();

                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

                mimeMessageHelper.setFrom(fromEmail);
                mimeMessageHelper.setTo(customerInfoDto.getClientEmail());
                mimeMessageHelper.setSubject(subject);
                mimeMessageHelper.setText(text);

                javaMailSender.send(mimeMessage);
                log.info("Mail Sent Successfully...");
            }catch (Exception e){
                throw new RuntimeException("Failed to send mail: " + e.getMessage(), e);
            }
        }
    }

}
