//package az.digital.crystalabsheronapp.service;
//
//import az.digital.crystalabsheronapp.dao.entity.CustomerInfo;
//import az.digital.crystalabsheronapp.dao.entity.Mail;
//import az.digital.crystalabsheronapp.dao.repository.CustomerRepository;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.LoggerFactory;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.logging.Logger;
//
//@RequiredArgsConstructor
//public class CustomerCheckService {
//
//    private final EmailService emailService;
//    private final CustomerRepository clientRepository;
//    private static final Logger logger = (Logger) LoggerFactory.getLogger(CustomerCheckService.class);
//
//    @Scheduled(cron = "0 0 0 0 0 3")
//    public void checkPayments() {
//        LocalDate inThreeDays = LocalDate.parse(LocalDateTime.now());
//        List<CustomerInfo> clientsDueIn3Days = clientRepository.findClientsWithPaymentDueIn3Days(inThreeDays);
//        for (CustomerInfo client : clientsDueIn3Days) {
//            Mail mail1 = new Mail();
//            emailService.sendMail(mail1);
//        }
//
//    }
//}
//
//
