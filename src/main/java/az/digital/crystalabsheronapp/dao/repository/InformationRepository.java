package az.digital.crystalabsheronapp.dao.repository;


import az.digital.crystalabsheronapp.dao.entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepository
        extends JpaRepository<Information, Long> {

}
