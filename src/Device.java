public class Device implements Comparable<Object>{
    private String name;
    private int weight;
    private int useScore;
    private int requiredUnits;

    Device(String name, int weight, int useScore, int requiredUnits){
        this.name=name;
        this.weight=weight;
        this.useScore=useScore;
        this.requiredUnits=requiredUnits;
    }

    public int getUseScore() {
        return useScore;
    }

    public int getRequiredUnits() {
        return requiredUnits;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public void setRequiredUnits(int requiredUnits) {
        this.requiredUnits = requiredUnits;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUseScore(int useScore) {
        this.useScore = useScore;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Device{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", useScore=" + useScore +
                ", requiredUnits=" + requiredUnits +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Device)) throw new IllegalArgumentException("Can't compare Device to non-Device-object!");

        Device d = (Device) o;
        double valueDevice = ((double)d.getUseScore())/((double)d.getWeight());
        double valueThis = ((double)this.getUseScore())/((double)this.getWeight());

        //return Double.compare(valueDevice, valueThis);
        return Double.compare(valueThis, valueDevice);
    }


}
