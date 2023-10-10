package az.digital.crystalabsheronapp.dao.repository;


import az.digital.crystalabsheronapp.dao.entity.CustomerInfo;

import java.time.LocalDate;
import java.util.List;

public interface ClientRepository {

    List<CustomerInfo> findClientsWithPaymentDueIn3Days(LocalDate inThreeDays);
    List<CustomerInfo> findClientsWithPaymentDueToday();
}
