package az.digital.crystalabsheronapp.dao.repository;

import az.digital.crystalabsheronapp.dao.entity.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends JpaRepository<Block,Long> {
}
