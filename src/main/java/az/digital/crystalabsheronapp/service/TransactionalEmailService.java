package az.digital.crystalabsheronapp.service;

import az.digital.crystalabsheronapp.dao.entity.CustomerInfo;
import az.digital.crystalabsheronapp.dao.repository.CustomerInfoRepository;
import az.digital.crystalabsheronapp.dto.CustomerInfoDto;
import az.digital.crystalabsheronapp.exceptions.NoSuchCustomerException;
import az.digital.crystalabsheronapp.mapper.CustomerInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Service
@RequiredArgsConstructor
public class TransactionalEmailService {
    private final CustomerInfoRepository customerInfoRepository;
    private final EmailService emailService;
    private final CustomerInfoMapper customerInfoMapper;

    public ResponseEntity<List<CustomerInfo>> getAllExamples(){
        List<CustomerInfo> all = customerInfoRepository.findAll();
        return null;
    }

    public void warningMail(CustomerInfoDto customerInfoDto){
        CustomerInfo save = customerInfoRepository.save(customerInfoMapper.fromDtoToEntity(customerInfoDto));
        Timer timer = new Timer();
        TimerTask threeDaysLeft = new TimerTask() {
            @Override
            public void run() {
                CustomerInfoDto info = new CustomerInfoDto();
                info.setCustomerName(customerInfoRepository.findById(save.getCustomerId()).get().getCustomerName());
                info.setCustomerSurname(customerInfoRepository.findById(save.getCustomerId()).get().getCustomerSurname());
                info.setClientEmail(customerInfoRepository.findById(save.getCustomerId()).get().getClientEmail());
                info.setCreationTime(customerInfoRepository.findById(save.getCustomerId()).get().getCreationTime());
                LocalDateTime paymentDueDate = save.getCreationTime();
                LocalDateTime notificationDate = paymentDueDate.minusDays(3);

                emailService.sendMail(info,"Ödəniş haqqında məlumat","Hörmətli"+save.getCustomerName()+". Ödənişinizə 3 gün qalıb.");
            }
        };

        TimerTask paymentDay = new TimerTask() {
            @Override
            public void run() {
                CustomerInfoDto info = new CustomerInfoDto();
                info.setCustomerName(customerInfoRepository.findById(save.getCustomerId()).get().getCustomerName());
                info.setCustomerSurname(customerInfoRepository.findById(save.getCustomerId()).get().getCustomerSurname());
                info.setClientEmail(customerInfoRepository.findById(save.getCustomerId()).get().getClientEmail());
                info.setCreationTime(customerInfoRepository.findById(save.getCustomerId()).get().getCreationTime());

                emailService.sendMail(info,"Ödəniş haqqında məlumat","Hörmətli"+save.getCustomerName()+". Bugün, ödəniş gününüzdür.");
            }
        };
        TimerTask threeDaysAfterPayment = new TimerTask() {
            @Override
            public void run() {
                CustomerInfoDto info = new CustomerInfoDto();
                info.setCustomerName(customerInfoRepository.findById(save.getCustomerId()).get().getCustomerName());
                info.setCustomerSurname(customerInfoRepository.findById(save.getCustomerId()).get().getCustomerSurname());
                info.setClientEmail(customerInfoRepository.findById(save.getCustomerId()).get().getClientEmail());
                info.setCreationTime(customerInfoRepository.findById(save.getCustomerId()).get().getCreationTime());

                emailService.sendMail(info,"Ödəniş haqqında məlumat","Hörmətli"+save.getCustomerName()+". Bugün, ödəniş günündən 3 gün keçib.");
            }
        };

//        timer.schedule(threeDaysLeft,);
    }


    public void updatePaymentDueDate(Long customerId, LocalDateTime newDate){
        customerInfoRepository.findById(customerId).orElseThrow(()-> new NoSuchCustomerException("The "));
    }
}
