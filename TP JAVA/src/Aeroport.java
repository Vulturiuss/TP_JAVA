public class Aeroport {

    //Attributs
    private String IATA;
    private String name;
    private double latitude;
    private double longitude;

    //Constructeur
    public Aeroport(String IATA, String name, double latitude, double longitude){
        this.IATA = IATA;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getIATA() {
        return IATA;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    // getter
    public String getCode() {
        return IATA;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "Aéroport " + name + " (" + IATA + "), "
                + " - "
                + "Latitude: " + latitude + ", Longitude: " + longitude;
    }

    public double calculDistance(Aeroport a) {
        double theta1 = Math.toRadians(this.latitude);
        double phi1 = Math.toRadians(this.longitude);
        double theta2 = Math.toRadians(a.latitude);
        double phi2 = Math.toRadians(a.longitude);

        double norme = Math.pow(theta2 - theta1, 2) + Math.pow((phi2 - phi1) * Math.cos((theta2 + theta1) / 2), 2);

        return norme;
    }


}
