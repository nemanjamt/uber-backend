package faculty.project.uber.model.others;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float length;
    @OneToMany(mappedBy = "route", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private Set<RouteCoordinates> routeCoordinates;
//    @OneToMany
//    private Set<RoutePart> routeParts;

}
