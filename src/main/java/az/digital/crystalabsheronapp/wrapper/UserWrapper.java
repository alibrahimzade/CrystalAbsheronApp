package az.digital.crystalabsheronapp.wrapper;

import az.digital.crystalabsheronapp.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWrapper {
    Long id;

    String name;

    String surname;

    String fatherName;

    String fin;

    String password;

    Role role;
}
