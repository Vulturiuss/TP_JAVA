import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class JsonFlightFiller {
    private ArrayList<Flight> list = new ArrayList<>();

    public JsonFlightFiller(String jsonString, World w) {
        try {
            InputStream is = new ByteArrayInputStream(jsonString.getBytes("UTF-8"));
            JsonReader reader = Json.createReader(is);
            JsonObject root = reader.readObject();
            
            JsonArray data = root.getJsonArray("data");
            
            for (int i = 0; i < data.size(); i++) {
                JsonObject flightObj = data.getJsonObject(i);
                
                try {
                    JsonObject flight = flightObj.getJsonObject("flight");
                    String flightIata = flight.isNull("iata") ? null : flight.getString("iata");
                    
                    JsonObject departure = flightObj.getJsonObject("departure");
                    String departureAirport = departure.isNull("airport") ? null : departure.getString("airport");
                    String departureIata = departure.isNull("iata") ? null : departure.getString("iata");
                    
                    JsonObject arrival = flightObj.getJsonObject("arrival");
                    String arrivalAirport = arrival.isNull("airport") ? null : arrival.getString("airport");
                    String arrivalIata = arrival.isNull("iata") ? null : arrival.getString("iata");
                    
                    JsonObject airline = flightObj.getJsonObject("airline");
                    String airlineName = airline.isNull("name") ? null : airline.getString("name");
                    
                    String flightStatus = flightObj.isNull("flight_status") ? null : flightObj.getString("flight_status");
                    
                    if (flightIata != null && departureIata != null && arrivalIata != null) {
                        Flight f = new Flight(flightIata, airlineName, departureAirport,
                                departureIata, arrivalAirport, arrivalIata, flightStatus);
                        list.add(f);
                    }
                } catch (Exception e) {
                    // Ignorer les vols avec données incomplètes
                }
            }
            
            reader.close();
        } catch (Exception e) {
            System.out.println("Erreur lors du parsing JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<Flight> getList() {
        return list;
    }

    public static void main(String[] args) {
        try {
            World w = new World("./data/airport-codes_no_comma.csv");
            java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("data/test.txt"));
            String test = br.readLine();
            br.close();
            JsonFlightFiller jsonFlightFiller = new JsonFlightFiller(test, w);
            System.out.println("Nombre de vols parsés: " + jsonFlightFiller.getList().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

