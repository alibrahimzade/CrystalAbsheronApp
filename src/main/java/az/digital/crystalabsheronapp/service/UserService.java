package az.digital.crystalabsheronapp.service;

import az.digital.crystalabsheronapp.dao.entity.User;
import az.digital.crystalabsheronapp.dao.repository.UserRepository;
import az.digital.crystalabsheronapp.dto.UserDto;
import az.digital.crystalabsheronapp.mapper.UserMapper;
import az.digital.crystalabsheronapp.wrapper.UserWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public ResponseEntity<List<UserWrapper>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.getAllUsers());
    }

    public ResponseEntity<UserDto> getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseGet(null);
        if (Objects.nonNull(user)) {
            return ResponseEntity.ok(userMapper.fromEntityToDto(user));
        }
        return ResponseEntity.status(NOT_FOUND).build();
    }

    public ResponseEntity<UserDto> updateUser(Long userId, UserDto userDto) {

        User user = userRepository.findById(userId).orElseGet(null);
        if (Objects.nonNull(user)) {
            User update = userMapper.fromDtoToEntity(userDto);
            return ResponseEntity.status(OK).body(userMapper.fromEntityToDto(userRepository.save(update)));
        }
        return ResponseEntity.status(NOT_FOUND).build();
    }

    public ResponseEntity<UserDto> deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseGet(null);
        if (Objects.nonNull(user)) {
            userRepository.deleteById(userId);
            return ResponseEntity.status(OK).build();
        }
        return ResponseEntity.status(NOT_FOUND).build();
    }
}
