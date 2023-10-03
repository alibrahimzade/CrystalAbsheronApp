package az.digital.crystalabsheronapp.service;

import az.digital.crystalabsheronapp.dao.entity.User;
import az.digital.crystalabsheronapp.dao.repository.UserRepository;
import az.digital.crystalabsheronapp.dto.UserDto;
import az.digital.crystalabsheronapp.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.hibernate.engine.spi.Status.DELETED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }

    public ResponseEntity<?> getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseGet(null);
        if (Objects.nonNull(user)) {
            return ResponseEntity.ok(UserMapper.PROFILE_MAPPER.buildEntityToDto(user));
        }
        return ResponseEntity.status(NOT_FOUND).body("THIS USER IS NOT FOUND");
    }

    public ResponseEntity<?> updateUser(Long userId, UserDto userDto) {

        User user = userRepository.findById(userId).orElseGet(null);
        if (Objects.nonNull(user)) {
            User update = UserMapper.PROFILE_MAPPER.buildDtoToEntity(userDto);
            return ResponseEntity.ok(userRepository.save(update));
        }
        return ResponseEntity.status(NOT_FOUND).body("THIS USER IS NOT FOUND");
    }

    public ResponseEntity<?> deleteUser(Long userId){
        User user = userRepository.findById(userId).orElseGet(null);
        if (Objects.nonNull(user)){
            userRepository.deleteById(userId);
            return ResponseEntity.ok(DELETED);
        }
        return ResponseEntity.status(NOT_FOUND).body("THIS USER DOES NOT EXIST");
    }
}
