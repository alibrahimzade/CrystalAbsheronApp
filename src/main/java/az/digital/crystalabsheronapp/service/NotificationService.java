package az.digital.crystalabsheronapp.service;

import az.digital.crystalabsheronapp.dao.entity.CustomerInfo;
import az.digital.crystalabsheronapp.dao.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final CustomerRepository customerRepository;

    /**
     * Fetches all customers who have a payment due in 3 days.
     *
     * @return List of customers with payment due in 3 days.
     */
    public List<CustomerInfo> getCustomersWithUpcomingPayments() {
        LocalDate thresholdDate = LocalDate.now().plusDays(3);
        return customerRepository.findAllByPaymentDate(thresholdDate);
    }
}

