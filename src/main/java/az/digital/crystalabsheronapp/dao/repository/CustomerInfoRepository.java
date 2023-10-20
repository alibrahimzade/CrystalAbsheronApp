package az.digital.crystalabsheronapp.dao.repository;


import az.digital.crystalabsheronapp.dao.entity.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerInfoRepository extends JpaRepository<CustomerInfo,Long> {
    Optional<CustomerInfo> getCustomerInfoByCustomerName(String name);
}
