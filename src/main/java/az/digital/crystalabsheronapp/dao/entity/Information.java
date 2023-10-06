package az.digital.crystalabsheronapp.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "information")
public class Information {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String age;
    private String phone;
    private String email;
    private String field;
    private String fieldSpec;
    private String whyUs;
    private String howContact;
    private String dateContact;
    private String feedback;
}