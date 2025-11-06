//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Aeroport cdg = new Aeroport("CDG", "Charles de Gaulle", 49.0097, 2.5479);
        System.out.println(cdg);

        World w = new World("./data/airport-codes_no_comma.csv");
        System.out.println("Found " + w.getList().size() + " airports.");

        Aeroport paris = w.findNearestAirport(2.316, 48.866);
        System.out.println("Aeroport le plus proche de Paris: " + paris);
    }
}