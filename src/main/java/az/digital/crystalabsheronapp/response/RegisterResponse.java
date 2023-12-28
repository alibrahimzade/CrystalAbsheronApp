package az.digital.crystalabsheronapp.response;

import az.digital.crystalabsheronapp.dao.entity.User;
import az.digital.crystalabsheronapp.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterResponse {
    String name;
    String surname;
    String fatherName;
    String fin;
    String password;
    Role role;
    public static RegisterResponse buildRegisterDto(User userEntity) {
        return RegisterResponse.builder()
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .fatherName(userEntity.getFatherName())
                .fin(userEntity.getFin())
                .password(userEntity.getPassword())
                .role(userEntity.getRole())
                .build();
    }
}
