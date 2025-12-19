public class Flight {
    private String flightIata;
    private String airlineName;
    private String departureAirport;
    private String departureIata;
    private String arrivalAirport;
    private String arrivalIata;
    private String flightStatus;

    public Flight(String flightIata, String airlineName, String departureAirport,
                  String departureIata, String arrivalAirport, String arrivalIata,
                  String flightStatus) {
        this.flightIata = flightIata;
        this.airlineName = airlineName;
        this.departureAirport = departureAirport;
        this.departureIata = departureIata;
        this.arrivalAirport = arrivalAirport;
        this.arrivalIata = arrivalIata;
        this.flightStatus = flightStatus;
    }

    public String getFlightIata() {
        return flightIata;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public String getDepartureIata() {
        return departureIata;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public String getArrivalIata() {
        return arrivalIata;
    }

    public String getFlightStatus() {
        return flightStatus;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightIata='" + flightIata + '\'' +
                ", airlineName='" + airlineName + '\'' +
                ", departureAirport='" + departureAirport + '\'' +
                ", departureIata='" + departureIata + '\'' +
                ", arrivalAirport='" + arrivalAirport + '\'' +
                ", arrivalIata='" + arrivalIata + '\'' +
                ", flightStatus='" + flightStatus + '\'' +
                '}';
    }
}

