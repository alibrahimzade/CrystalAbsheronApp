package az.digital.crystalabsheronapp.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Mail {

    private String toMail;
    private String subject;
    private String body;


}
