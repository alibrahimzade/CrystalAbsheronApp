package az.digital.crystalabsheronapp.dao.repository;

import az.digital.crystalabsheronapp.dao.entity.Contract;
import az.digital.crystalabsheronapp.wrapper.ContractWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findAllByHasPaid(boolean hasPaid);

    List<ContractWrapper> getAllContracts();
}
