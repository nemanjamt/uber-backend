package faculty.project.uber.dto.report.response;

import java.text.DecimalFormat;
import java.util.Date;

public class ReportResponse {
    private Date date;
    private long rides;
    private double distance;
    private double revenue;

    public ReportResponse(Date date, long rides, Double distance, double revenue) {
        DecimalFormat df = new DecimalFormat("#.##");
        this.date = date;
        this.rides = rides;
        this.distance =Double.valueOf(df.format(distance/1000));
        this.revenue = revenue;
    }

    public ReportResponse(Object[] objects){

        DecimalFormat df = new DecimalFormat("#.##");
        this.date = (Date) objects[0];
        this.rides = (long) objects[1];
        this.distance  = Double.valueOf(df.format((double) objects[2]));
        this.revenue = (double) objects[3];
    }

    public ReportResponse(){

    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setRides(long rides) {
        this.rides = rides;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public Date getDate() {
        return date;
    }

    public long getRides() {
        return rides;
    }

    public Double getDistance() {
        return distance;
    }

    public double getRevenue() {
        return revenue;
    }

    @Override
    public String toString() {
        return "ReportResponse{" +
                "date=" + date +
                ", rides=" + rides +
                ", distance=" + distance +
                ", revenue=" + revenue +
                '}';
    }
}
