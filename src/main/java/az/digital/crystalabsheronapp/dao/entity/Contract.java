package az.digital.crystalabsheronapp.dao.entity;

import az.digital.crystalabsheronapp.enums.Payments;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@NamedQuery( name = "Contract.getAllContracts",
        query = "select new az.digital.crystalabsheronapp.wrapper.ContractWrapper" +
                "(c.id, c.detail,c.status,c.closeDate,c.creationTime,c.customerInfo)from Contract c ")

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "contract")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;//optional (rovshan??)

    String detail;

    @Enumerated(EnumType.STRING)
    Payments status;

    boolean hasPaid;


    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd-mm-yyyy'T'HH:mm")
    @Column(name = "muqavilenin yaradilma vaxti")
    LocalDateTime creationTime;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd-mm-yyyy'T'HH:mm")
    @Column(name = "muqavilenin bitme vaxti")
    LocalDateTime closeDate;

    @ManyToOne
    @JoinColumn(name = "customer_info_id")
    @JsonIgnore
    CustomerInfo customerInfo;

}
