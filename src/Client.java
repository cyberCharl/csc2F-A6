public class Client {

    private String P;
    private String D;

    public Client(String pickUp, String dropOff) {
        P = pickUp;
        D = dropOff;
    }

    public String getCP() {
        return P;
    }

    public String getCD() { return D; }

    public String getClient() {
        return D + " " + P;
    }

    public String toString() {
        return "client " + P + " " + D;
    }
}
