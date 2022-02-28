public class Truck {

    private String TL;

    public Truck(String truckLocation) {
        TL = truckLocation;
    }

    public String getTL() {
        return TL;
    }

    public String toString() {
        return "truck " + TL;
    }
}
