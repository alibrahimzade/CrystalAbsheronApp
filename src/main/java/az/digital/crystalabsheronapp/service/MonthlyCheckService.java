package az.digital.crystalabsheronapp.service;

import az.digital.crystalabsheronapp.dao.entity.CustomerInfo;
import az.digital.crystalabsheronapp.dao.entity.Mail;
import az.digital.crystalabsheronapp.dao.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class MonthlyCheckService {

    private final EmailService emailService;
    private final ClientRepository clientRepository;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(MonthlyCheckService.class);

    @Scheduled(cron = "0 0 0 * * ?")
    public void checkPayments(Mail mail) {
        LocalDate inThreeDays = LocalDate.now().plusDays(3);

        List<CustomerInfo> clientsDueIn3Days = clientRepository.findClientsWithPaymentDueIn3Days(inThreeDays);
        for (CustomerInfo client : clientsDueIn3Days) {
            Mail mail1=new Mail();
            emailService.sendMail(mail);
        }

    }
}


