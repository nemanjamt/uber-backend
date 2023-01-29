package faculty.project.uber.dto.report.response;

import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;

@Getter
@Setter

public class ReportSummaryResponse {
    private long totalRides;
    private double averageRides;
    private double totalMoney;
    private double averageMoney;
    private double totalDistance;
    private double averageDistance;

    public ReportSummaryResponse(Long totalRides, Integer dayNumber, Double averageMoney, Double totalMoney,  Double averageDistance,Double totalDistance) {
        DecimalFormat df = new DecimalFormat("#.##");
        this.totalRides = totalRides;
        this.averageRides = dayNumber != 0 ? Double.valueOf(df.format((double)totalRides/dayNumber)):0;
        this.totalMoney = Double.valueOf(df.format(totalMoney)) ;
        this.averageMoney = Double.valueOf(df.format(averageMoney)) ;
        this.totalDistance = Double.valueOf(df.format(totalDistance/1000)) ;
        this.averageDistance = Double.valueOf(df.format(averageDistance/1000)) ;


    }

    @Override
    public String toString() {
        return "ReportSummaryResponse{" +
                "totalRides=" + totalRides +
                ", averageRides=" + averageRides +
                ", totalMoney=" + totalMoney +
                ", averageMoney=" + averageMoney +
                ", totalDistance=" + totalDistance +
                ", averageDistance=" + averageDistance +
                '}';
    }
}
