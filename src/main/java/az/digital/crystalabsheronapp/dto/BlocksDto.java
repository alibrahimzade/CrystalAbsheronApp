package az.digital.crystalabsheronapp.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlocksDto {
    Long id;
    String name;
    Long residenceId;
}
