package az.digital.crystalabsheronapp.dao.repository;

import az.digital.crystalabsheronapp.dao.entity.User;
import az.digital.crystalabsheronapp.wrapper.UserWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> getUserByFin(String fin);
    List<UserWrapper>getAllUsers();
}
