package az.digital.crystalabsheronapp.service;

import az.digital.crystalabsheronapp.dao.entity.User;
import az.digital.crystalabsheronapp.dao.repository.UserRepository;
import az.digital.crystalabsheronapp.enums.Role;
import az.digital.crystalabsheronapp.exceptions.UserAlreadyExist;
import az.digital.crystalabsheronapp.exceptions.UserNotFoundException;
import az.digital.crystalabsheronapp.request.AuthenticationRequest;
import az.digital.crystalabsheronapp.request.RegisterRequest;
import az.digital.crystalabsheronapp.response.AuthenticationResponse;
import az.digital.crystalabsheronapp.response.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public RegisterResponse register(RegisterRequest request) {
        var exist = userRepo.getUserByFin(request.getFin()).isPresent();
        if (exist) {
            throw new UserAlreadyExist("User already exist");
        }
        var user = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .fatherName(request.getFatherName())
                .fin(request.getFin())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        var userEntity = userRepo.save(user);
        return RegisterResponse.buildRegisterDto(userEntity);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getFin(),
                        request.getPassword()
                )
        );
        var user = userRepo.getUserByFin(request.getFin())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
