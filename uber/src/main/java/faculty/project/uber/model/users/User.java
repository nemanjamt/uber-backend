package faculty.project.uber.model.users;




import faculty.project.uber.model.others.Coordinates;
import faculty.project.uber.model.others.Photo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import faculty.project.uber.model.others.Role;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User  {

    @Id
    @SequenceGenerator(name = "SeqGenV1", sequenceName = "SeqV1", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqGenV1")
    private Long id;
    private String name;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String phone;
    @OneToOne
    private Photo photo;
    private Boolean blocked;
    @ManyToMany
    private List<Role> roles;
    @OneToOne
    private Coordinates address;
}

