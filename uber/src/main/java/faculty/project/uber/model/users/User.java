package faculty.project.uber.model.users;




import faculty.project.uber.model.others.Coordinates;
import faculty.project.uber.model.others.Photo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import faculty.project.uber.model.others.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User  implements UserDetails {

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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  this.roles;
    }

    public String getRoles(){ return this.roles.get(0).getName();}

    public List<Role> getUserRoles() {return this.roles;}

    private String address;
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

