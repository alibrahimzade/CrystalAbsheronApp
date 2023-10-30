package az.digital.crystalabsheronapp.service;

import az.digital.crystalabsheronapp.dao.entity.User;
import az.digital.crystalabsheronapp.dao.repository.UserRepository;
import az.digital.crystalabsheronapp.dto.UserDto;
import az.digital.crystalabsheronapp.exceptions.UserNotFoundException;
import az.digital.crystalabsheronapp.mapper.UserMapper;
import az.digital.crystalabsheronapp.wrapper.UserWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        // Create a list of UserWrappers for testing
        List<UserWrapper> userWrappers = List.of(new UserWrapper(), new UserWrapper());
        when(userRepository.getAllUsers()).thenReturn(userWrappers);

        // Call the service method
        ResponseEntity<List<UserWrapper>> responseEntity = userService.getAllUsers();

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userWrappers, responseEntity.getBody());
    }

    @Test
    public void testGetUserById() {
        // Create a User for testing
        User user = new User();
        Long userId = 1L; // Assuming a valid User ID
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Create a UserDto for testing
        UserDto userDto = new UserDto();
        when(userMapper.fromEntityToDto(user)).thenReturn(userDto);

        // Call the service method
        ResponseEntity<UserDto> responseEntity = userService.getUserById(userId);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userDto, responseEntity.getBody());
    }

    @Test
    public void testGetUserById_UserNotFound() { //TODO: does not work
        // Create a User with an invalid User ID
        Long nonExistentUserId = 999L; // Assuming an invalid User ID
        when(userRepository.findById(nonExistentUserId)).thenReturn(Optional.empty());

        // Call the service method and assert that it throws the expected exception
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(nonExistentUserId));
    }
}
