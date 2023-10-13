//package az.digital.crystalabsheronapp.service;
//
//import az.digital.crystalabsheronapp.dao.entity.CustomerInfo;
//import az.digital.crystalabsheronapp.dao.entity.Mail;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import java.util.List;
//
//@RequiredArgsConstructor
//@Slf4j
//public class PaymentReminderJob implements Job {
//
//    private final NotificationService notificationService;
//
//    private final EmailService emailService;
//
//    @Override
//    public void execute(JobExecutionContext context) throws JobExecutionException {
//        List<CustomerInfo> customers = notificationService.getCustomersWithUpcomingPayments();
//
//        for (CustomerInfo customer : customers) {
//            Mail mail = new Mail();
//            mail.setToMail(customer.getClientEmail());
//            mail.setSubject("Payment notification");
//            mail.setBody("You will be paid in 3 days.");
//            emailService.sendMail(mail);
//        }
//    }
//}
