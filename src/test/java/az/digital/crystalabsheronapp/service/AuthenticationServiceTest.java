package az.digital.crystalabsheronapp.service;

import az.digital.crystalabsheronapp.dao.entity.User;
import az.digital.crystalabsheronapp.dao.repository.UserRepository;
import az.digital.crystalabsheronapp.enums.Role;
import az.digital.crystalabsheronapp.exceptions.UserAlreadyExistException;
import az.digital.crystalabsheronapp.exceptions.UserNotFoundException;
import az.digital.crystalabsheronapp.request.AuthenticationRequest;
import az.digital.crystalabsheronapp.request.RegisterRequest;
import az.digital.crystalabsheronapp.response.AuthenticationResponse;
import az.digital.crystalabsheronapp.response.RegisterResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegister_Success() {
        RegisterRequest registerRequest = new RegisterRequest("John", "Doe", "Father", "123456", "password");
        User user = new User(1L, "John", "Doe", "Father", "123456", "encodedPassword", Role.USER);

        when(userRepository.getUserByFin("123456")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        RegisterResponse response = authenticationService.register(registerRequest);

        assertNotNull(response);
        assertEquals("John", response.getName());
        assertEquals("Doe", response.getSurname());
        assertEquals("Father", response.getFatherName());
        assertEquals("123456", response.getFin());
        assertEquals(Role.USER, response.getRole());
    }

    @Test
    public void testRegister_UserAlreadyExists() {
        RegisterRequest registerRequest = new RegisterRequest("John", "Doe", "Father", "123456", "password");

        when(userRepository.getUserByFin("123456")).thenReturn(Optional.of(new User()));

        assertThrows(UserAlreadyExistException.class, () -> authenticationService.register(registerRequest));
    }

    @Test
    public void testAuthenticate_Success() {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("123456", "password");
        User user = new User(1L, "John", "Doe", "Father", "123456", "encodedPassword", Role.USER);
        when(userRepository.getUserByFin("123456")).thenReturn(Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn("jwtToken");

        AuthenticationResponse response = authenticationService.authenticate(authenticationRequest);

        assertNotNull(response);
        assertEquals("jwtToken", response.getToken());
    }

    @Test
    public void testAuthenticate_UserNotFound() {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("123456", "password");
        when(userRepository.getUserByFin("123456")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> authenticationService.authenticate(authenticationRequest));
    }
}
