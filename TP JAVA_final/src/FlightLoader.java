import javafx.application.Platform;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class FlightLoader implements Runnable {
    private World world;
    private Earth earth;
    private String apiKey;

    public FlightLoader(World world, Earth earth, String apiKey) {
        this.world = world;
        this.earth = earth;
        this.apiKey = apiKey;
    }

    @Override
    public void run() {
        try {
            String url = "http://api.aviationstack.com/v1/flights?access_key=" + apiKey + "&arr_iata=CDG";
            
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonResponse = response.body();
            
            JsonFlightFiller filler = new JsonFlightFiller(jsonResponse, world);
            ArrayList<Flight> flights = filler.getList();
            
            for (Flight flight : flights) {
                Aeroport dep = world.findByCode(flight.getDepartureIata());
                Aeroport arr = world.findByCode(flight.getArrivalIata());
                
                if (dep != null && arr != null) {
                    final Aeroport finalDep = dep;
                    final Aeroport finalArr = arr;
                    Platform.runLater(() -> {
                        earth.displayYellowBall(finalDep);
                        earth.displayYellowBall(finalArr);
                    });
                }
            }
            
            System.out.println("Chargement terminé : " + flights.size() + " vols traités.");
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement des vols: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

