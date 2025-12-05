import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

public class Earth extends Group {

    private final Sphere sph;
    private final PhongMaterial texture;
    private Rotate ry;

    public Earth() {
        sph = new Sphere(300);
        texture = new PhongMaterial();

        Image img = null;
        try {
            img = new Image(Earth.class.getResource("/resources/earth.jpg").toExternalForm());
        } catch (Exception e) {
            System.out.println("Texture earth.jpg introuvable dans /resources. La sphère sera sans texture.");
        }
        if (img != null && !img.isError()) {
            texture.setDiffuseMap(img);
        }

        sph.setMaterial(texture);
        this.getChildren().add(sph);

        ry = new Rotate(0, Rotate.Y_AXIS);
        this.getTransforms().add(ry);

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long time) {
                double angle = (time / 1_000_000_000.0) * (360.0 / 15.0);
                ry.setAngle(angle);
            }
        };
        animationTimer.start();
    }

    public Sphere createSphere(Aeroport a, Color color) {
        double R = 300;

        double theta = Math.toRadians(a.getLatitude());
        double phi = Math.toRadians(a.getLongitude());

        double x = R * Math.cos(theta) * Math.sin(phi);
        double y = -R * Math.sin(theta);
        double z = -R * Math.cos(theta) * Math.cos(phi);

        Sphere s = new Sphere(2);
        s.setTranslateX(x);
        s.setTranslateY(y);
        s.setTranslateZ(z);

        PhongMaterial mat = new PhongMaterial(color);
        s.setMaterial(mat);

        return s;
    }

    public void displayRedSphere(Aeroport a) {
        Sphere s = createSphere(a, Color.RED);
        this.getChildren().add(s);
    }
}
