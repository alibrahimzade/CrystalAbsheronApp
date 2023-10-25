package az.digital.crystalabsheronapp.service;

import az.digital.crystalabsheronapp.dao.entity.CustomerInfo;
import az.digital.crystalabsheronapp.dao.entity.Mail;
import az.digital.crystalabsheronapp.dao.repository.CustomerInfoRepository;
import az.digital.crystalabsheronapp.dto.CustomerInfoDto;
import az.digital.crystalabsheronapp.exceptions.NoSuchCustomerException;
import az.digital.crystalabsheronapp.mapper.CustomerInfoMapper;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static az.digital.crystalabsheronapp.enums.Payments.IS_LATE;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender javaMailSender;
    private final CustomerInfoRepository customerInfoRepository;
    private final CustomerInfoMapper customerInfoMapper;

    @Scheduled(cron = "0 0 12 * * ?")// Cron expression to run the task at 4 PM every day
    public void sendMonthlyEmail() {
        List<CustomerInfo> all = customerInfoRepository.findAll();
        List<CustomerInfoDto> customers = customerInfoMapper.fromEntityListToDtoList(all);
        for (CustomerInfoDto customer : customers) {
            LocalDate currentDate= LocalDate.now();
            LocalDate paymentDate = customer.getPaymentDate();
            try {
                MimeMessage message = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                if (customer.getStatus()== IS_LATE){
                    if (currentDate.isEqual(paymentDate.minusDays(3))){
                        helper.setTo(customer.getClientEmail());
                        helper.setSubject("Email warning");
                        helper.setText("Dear "+customer.getCustomerName()
                                +" "+customer.getCustomerSurname()+".Three days left for your payment");
                        javaMailSender.send(message);
                        log.info("Monthly email sent!");
                    }
                    if (currentDate.isEqual(paymentDate)){
                        helper.setTo(customer.getClientEmail());
                        helper.setSubject("Email warning");
                        helper.setText("Dear "+customer.getCustomerName()
                                +" "+customer.getCustomerSurname()+".Today is your payment day");
                        javaMailSender.send(message);
                        updatePaymentDueDate(customer);
                        log.info("Email sent!");
                    }
                    if (currentDate.isEqual(paymentDate.plusDays(3))){
                        helper.setTo(customer.getClientEmail());
                        helper.setSubject("Email warning");
                        helper.setText("Dear "+customer.getCustomerName()
                                +" "+customer.getCustomerSurname()+".Payment is late for three days");
                        javaMailSender.send(message);
                        log.info("Email sent!");
                    }
                }
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    public void updatePaymentDueDate(CustomerInfoDto customerInfoDto) {
        CustomerInfo customerInfo = customerInfoRepository.findById(customerInfoDto.getCustomerId()).
                orElseThrow(() -> new NoSuchCustomerException("The Customer does not exist"));

        LocalDate currentDate = customerInfo.getPaymentDate();
        LocalDate newDate = currentDate.plusMonths(1);
        customerInfo.setPaymentDate(newDate);
        customerInfoRepository.save(customerInfo);
    }
}
