package faculty.project.uber.dto.report.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RideReportRequest {
    private LocalDate startDate;
    private LocalDate endDate;

}
