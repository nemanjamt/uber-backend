package faculty.project.uber.model.others;

import faculty.project.uber.model.enums.PaymentType;
import faculty.project.uber.model.users.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private PaymentType paymentType;
    private LocalDate date;
    private float total;
    @ManyToOne
    private Client client;
}
