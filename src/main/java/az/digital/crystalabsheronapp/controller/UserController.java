package az.digital.crystalabsheronapp.controller;

import az.digital.crystalabsheronapp.dto.UserDto;
import az.digital.crystalabsheronapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto, @PathVariable(name = "id") Long userId) {
        return ResponseEntity.ok(userService.updateUser(userId, userDto));
    }

    @DeleteMapping("/deleteUserById/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long userId) {
        return userService.deleteUser(userId);
    }
}
