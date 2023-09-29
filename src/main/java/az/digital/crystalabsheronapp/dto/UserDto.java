package az.digital.crystalabsheronapp.dto;

import az.digital.crystalabsheronapp.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    Long id;
    String name;
    String surname;
    String fatherName;
    String fin;
    String password;
    @Enumerated(EnumType.STRING)
    Role role;
}
