package az.digital.crystalabsheronapp.service;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfigService {

    @Bean
    public JobDetail paymentReminderJobDetail() {
        return JobBuilder.newJob(PaymentReminderJob.class)
                .withIdentity("paymentReminderJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger paymentReminderJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInHours(24)
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(paymentReminderJobDetail())
                .withIdentity("paymentReminderTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
