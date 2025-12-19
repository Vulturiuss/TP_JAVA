import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.stage.Stage;

public class Interface extends Application {

    private double previousY;
    private World world;

    @Override
    public void start(Stage stage) {

        world = new World("./data/airport-codes_no_comma.csv");
        Earth earth = new Earth();
        
        // Chargement des vols dans un thread séparé
        FlightLoader loader = new FlightLoader(world, earth, "2676502335811ad4047ee5cb656cdafe");
        Thread thread = new Thread(loader);
        thread.start();

        Scene scene = new Scene(earth, 600, 400, true);
        stage.setTitle("Earth Viewer – TP Java");
        stage.setScene(scene);

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1000);
        camera.setNearClip(0.1);
        camera.setFarClip(2000.0);
        camera.setFieldOfView(35);
        scene.setCamera(camera);

        scene.addEventHandler(MouseEvent.ANY, event -> {
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                previousY = event.getSceneY();
                System.out.println("Clicked on : (" + event.getSceneX() + ", " + event.getSceneY() + ")");
            }

            if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                double deltaY = event.getSceneY() - previousY;
                camera.setTranslateZ(camera.getTranslateZ() + deltaY);
                previousY = event.getSceneY();
            }

            if (event.getButton() == MouseButton.SECONDARY
                    && event.getEventType() == MouseEvent.MOUSE_CLICKED) {

                PickResult pick = event.getPickResult();

                if (pick.getIntersectedNode() != null) {
                    double texX = pick.getIntersectedTexCoord().getX();
                    double texY = pick.getIntersectedTexCoord().getY();

                    double latitude = 180 * (0.5 - texY);
                    double longitude = 360 * (texX - 0.5);

                    Aeroport nearest = world.findNearestAirport(longitude, latitude);
                    System.out.println(nearest);

                    earth.displayRedSphere(nearest);
                }
            }
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
