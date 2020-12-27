import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LoadListGenerator {
    private static List<Device> devices;
    private static List<Vehicle> vehicles;



    private LoadListGenerator() {}


    public static void main(String[] args) {
        // Fill device list
        String[] names = new String[]{"Notebook Büro 13\"", "Notebook Büro 14\"", "Notebook Outdoor", "Mobiltelefon Büro", "Mobiltelefon Outdoor", "Mobiltelefon Heavy Duty", "Tablet Büro klein", "Tablet Büro groß", "Tablet outdoor klein", "Tablet outdoor groß"};
        int[] requiredUnits = new int[]{205, 420, 450, 60, 157, 220, 620, 250, 540, 370};
        int[] weights = new int[]{2451, 2978, 3625, 717, 988, 1220, 1405, 1455, 1690, 1980};
        int[] useScores = new int[]{40, 35, 80, 30, 60, 65, 40, 40, 45, 68};
        devices = IntStream.range(0, names.length).mapToObj(i -> new Device(names[i], weights[i], useScores[i], requiredUnits[i])).collect(Collectors.toCollection(ArrayList::new));

        // Fill vehicle list
        int[] transporterCapacities = new int[]{1100000, 1100000};
        int[] driverWeights = new int[]{72400, 85700};
        vehicles = IntStream.range(0, transporterCapacities.length).mapToObj(i -> new Vehicle(transporterCapacities[i], driverWeights[i])).collect(Collectors.toCollection(ArrayList::new));

        // Generate load list for vehicles
        int[][] quantities = generateLoadList(devices, vehicles);

        // Output load lists
        printQuantities(quantities);

    }

    public static int[][] generateLoadList(List<Device> devices, List<Vehicle> vehicles) {
        // Initialize quantities of devices per vehicle
        int[][] quantities = new int[vehicles.size()][];
        for (int i = 0; i < quantities.length; i++) {
            quantities[i] = new int[devices.size()];
        }

        // Comparator in Device-class means that this sort is sufficient here
        Collections.sort(devices);

        /*
        * For each vehicle, iterate over devices starting at most useful per weight
        * If the device still fits on the truck and is still required, it is added to the order list
        * */
        for (int i = 0, vehiclesSize = vehicles.size(); i < vehiclesSize; i++) {
            Vehicle currentVehicle = vehicles.get(i);
            int capacity = currentVehicle.getCapacity() - currentVehicle.getDriverWeight();
            for (int j = 0, devicesSize = devices.size(); j < devicesSize; j++) {
                Device device = devices.get(j);
                while (capacity >= device.getWeight() && device.getRequiredUnits() > 0) {
                    capacity -= device.getWeight();
                    device.setRequiredUnits(device.getRequiredUnits() - 1);
                    quantities[i][j]++;
                }
            }
        }
        return quantities;

    }

    private static void printQuantities(int[][] quantities) {
        int totalValue = 0;
        for (int i = 0; i < quantities.length; i++) {
            // i+1 because the task specifies vehicles 1 and 2 but arrays start at 0
            StringBuilder output = new StringBuilder("Fahrzeug " + (i+1) + " sollte wie folgt beladen werden:\n");
            // Iterate over devices and append quantities
            for (int j = 0; j < quantities[i].length; j++) {
                int quantity = quantities[i][j];
                Device device = devices.get(j);
                output.append(quantity).append("x ").append(device.getName()).append("\n");
                totalValue+=quantity*device.getUseScore();
            }
            System.out.println(output);
        }
        System.out.println("Gesamtnutzen: "+totalValue);
    }


}
