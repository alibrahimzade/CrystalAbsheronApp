package az.digital.crystalabsheronapp.service;

import az.digital.crystalabsheronapp.dao.entity.CustomerInfo;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.util.List;

@RequiredArgsConstructor
public class PaymentReminderJob implements Job {

    private final NotificationService notificationService;

    private final EmailService emailService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        List<CustomerInfo> customers = notificationService.getCustomersWithUpcomingPayments();

        for (CustomerInfo customer : customers) {
            emailService.sendMail(customer.getClientEmail(), "Payment notification", "You will be paid in 3 days.");
        }

    }
}
