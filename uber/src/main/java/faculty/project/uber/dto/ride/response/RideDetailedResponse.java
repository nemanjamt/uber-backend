package faculty.project.uber.dto.ride.response;

import faculty.project.uber.dto.coordinates.response.CoordinatesResponseDTO;
import faculty.project.uber.model.others.Ride;
import faculty.project.uber.model.others.RouteCoordinates;
import faculty.project.uber.model.users.Client;
import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Data
public class RideDetailedResponse {
    private Long driverId;
    private List<Long> clientsIds;

    private List<CoordinatesResponseDTO> coordinates;
    public RideDetailedResponse(Ride r){
        this.driverId = r.getDriver().getId();
        clientsIds = new ArrayList<>();
        for(Client client:r.getClients()){
            clientsIds.add(client.getId());
        }

        this.coordinates = r.getRoute().getRouteCoordinates()
                .stream()
                .sorted(Comparator.comparing(RouteCoordinates::getIndex))
                .map(c -> new CoordinatesResponseDTO(c))
                .toList();
    }
}
