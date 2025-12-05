import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class World {
    private List<Aeroport> list = new ArrayList<>();

    public World(String fileName) {
        try {
            BufferedReader buf = new BufferedReader(new FileReader(fileName));
            String s = buf.readLine(); // lire l'entête
            s = buf.readLine();        // passer la première ligne d'entête

            while (s != null) {
                s = s.replaceAll("\"", "");
                String[] fields = s.split(",");

                try {
                    if (fields.length > 12 && fields[1].equals("large_airport") && !fields[9].isEmpty()) {
                        String code = fields[9]; // code IATA
                        String name = fields[2];
                        double longitude = Double.parseDouble(fields[11]);
                        double latitude = Double.parseDouble(fields[12]);

                        Aeroport a = new Aeroport(code, name, latitude, longitude);
                        list.add(a);
                    }
                } catch (Exception e) {
                    System.out.println("Ligne ignorée : " + s);
                }

                s = buf.readLine();
            }

            buf.close();
            System.out.println("Import terminé : " + list.size() + " aéroports chargés.");

        } catch (Exception e) {
            System.out.println("Maybe the file isn't there?");
            e.printStackTrace();
        }
    }


    public List<Aeroport> getList() {
        return list;
    }

    public Aeroport findByCode(String code) {
        for (Aeroport a : list) {
            if (a.getCode().equalsIgnoreCase(code)) {
                return a;
            }
        }
        return null;
    }

    public Aeroport findNearestAirport(double longitude, double latitude) {
        Aeroport best = null;
        double bestDistance = Double.MAX_VALUE;

        Aeroport target = new Aeroport("tmp", "target", latitude, longitude);

        for (Aeroport a : list) {
            double dist = a.calculDistance(target);
            if (dist < bestDistance) {
                bestDistance = dist;
                best = a;
            }
        }
        return best;
    }

    /**
     * Méthode utilitaire pour comparer deux points géographiques
     */
    public double distance(double lon1, double lat1, double lon2, double lat2) {
        double theta1 = Math.toRadians(lat1);
        double phi1 = Math.toRadians(lon1);
        double theta2 = Math.toRadians(lat2);
        double phi2 = Math.toRadians(lon2);

        return Math.pow(theta2 - theta1, 2)
                + Math.pow((phi2 - phi1) * Math.cos((theta2 + theta1) / 2), 2);
    }
}