package az.digital.crystalabsheronapp.dao.repository;

import az.digital.crystalabsheronapp.dao.entity.Residence;
import az.digital.crystalabsheronapp.wrapper.ResidenceWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResidenceRepository extends JpaRepository<Residence,Long> {
    Optional<Residence> getResidenceByName(String name);
    List<ResidenceWrapper> getAllResidences();
}