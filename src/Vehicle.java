public class Vehicle {
    private int capacity;
    private  int driverWeight;

    Vehicle(int capacity, int driverWeight){
        this.capacity = capacity;
        this.driverWeight=driverWeight;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getDriverWeight() {
        return driverWeight;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setDriverWeight(int driverWeight) {
        this.driverWeight = driverWeight;
    }


    @Override
    public String toString() {
        return "Vehicle{" +
                "capacity=" + capacity +
                ", driverWeight=" + driverWeight +
                '}';
    }
}
