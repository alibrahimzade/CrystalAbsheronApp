package az.digital.crystalabsheronapp.dao.repository;


import az.digital.crystalabsheronapp.dao.entity.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface CustomerRepository extends JpaRepository<CustomerInfo,Long> {

    List<CustomerInfo> findClientsWithPaymentDueIn3Days(LocalDate inThreeDays);
    List<CustomerInfo> findClientsWithPaymentDueToday();
}
