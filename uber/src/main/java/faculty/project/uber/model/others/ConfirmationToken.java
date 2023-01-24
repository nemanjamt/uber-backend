package faculty.project.uber.model.others;

import faculty.project.uber.model.users.Client;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="confirmationToken")
@Getter
@Setter
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private long tokenid;

    @Column(name="confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = Client.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Client userEntity;

    public ConfirmationToken() {}

    public ConfirmationToken(Client userEntity) {
        this.userEntity = userEntity;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }
}
