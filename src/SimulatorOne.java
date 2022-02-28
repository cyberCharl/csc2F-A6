import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SimulatorOne {
    Graph graph = new Graph();
    public static String[] inputArr =  new String[100];
    ArrayList<Truck> truckArr = new ArrayList<Truck>();
    ArrayList<Client> clientArr = new ArrayList<Client>();


    public static void main(String[] args) {
        SimulatorOne simOne = new SimulatorOne();

        // readFile();
        readInput();

        simOne.parseAll();

        for (int c = 0; c < simOne.clientArr.size(); c++) {
            Client currentClient = simOne.clientArr.get(c);
            System.out.println(currentClient.toString());

            int truckIndex = simOne.whichTruck(currentClient);

            simOne.completeOrder(simOne.truckArr.get(truckIndex),
                    currentClient);
        }
    }

    // finds which truck travelled the least
    public int whichTruck(Client cl) {
        Truck truckKun = truckArr.get(0);
        double weigh = 1000;
        for (Truck truck : truckArr) {

            double current = graph.processWeight(truck, cl);

            if (current < weigh) {
                weigh = current;
                truckKun = truck;
            }
        }
        return truckArr.indexOf(truckKun);
    }

    public void completeOrder(Truck tru, Client cl) {
        String T = tru.getTL();
        String P = cl.getCP();
        String D = cl.getCD();

        if (graph.processWeight(tru, cl) < 0) {
            System.out.println("cannot be helped");
        }

        else {
            System.out.println(tru.toString());

            graph.processOrder(T, P);

            System.out.println("pickup " + P);

            graph.processOrder(P, D);

            System.out.println("dropoff " + D);

            graph.processOrder(D, T);

        }
    }

    // reads input file into array
    public static void readFile() {
        try {
            File inp = new File("src/input.txt");
            Scanner reader = new Scanner(inp); // System.in);

            int count = 0;

            while (reader.hasNextLine()) {
                inputArr[count] = reader.nextLine();
                count++;
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error has occurred");
            e.printStackTrace();
        }
    }

    // reads userInput into array
    public static void readInput() {
        Scanner reader = new Scanner(System.in);

        int count = 0;

        while (reader.hasNextLine()) {
            inputArr[count] = reader.nextLine();
            count++;
        }

        reader.close();
    }

    // does all bellow great success
    public void parseAll() {
        parseGraph();
        parseTruck();
        parseClient();
    }

    // creates the graph (I think)
    public void parseGraph() {
        for (int i = 1; i <= pInt(inputArr[0]); i++) {
            String[] line = inputArr[i].split(" ");
            if (line.length > 1) {
                for (int f = 1; f < (line.length); f+=2) {
                    graph.addEdge(line[0], line[f], Double.parseDouble(line[f + 1]));
                }
            }
        }
    }

    // adds truck locations
    public void parseTruck() {
        String[] truckLocations = inputArr[pInt(inputArr[0]) + 2].split(" ");
        for (int j = 0; j < pInt(inputArr[pInt(inputArr[0]) + 1]); j++) {
            truckArr.add(new Truck(truckLocations[j]));
        }
    }

    // adds client pickup and drop-Off stuff i hope
    public void parseClient() {
        String[] clientDeliveries = inputArr[pInt(inputArr[0]) + 4].split(" ");
        for (int k = 0; k < pInt(inputArr[pInt(inputArr[0]) + 3]) * 2; k+=2) {
            clientArr.add(new Client(clientDeliveries[k], clientDeliveries[k+1] ));
        }
    }

    // got lazy rewriting the Integer.parseInt() I use it a lot so this is faster
    public int pInt(String s) {
        return Integer.parseInt(s);
    }
}
