package faculty.project.uber.model.others;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Table(name="route_coordinates")
@Entity
@NoArgsConstructor
public class RouteCoordinates extends Coordinates {
    private int index;
    @ManyToOne
    @JoinColumn(name = "route_id")
    Route route;
}
