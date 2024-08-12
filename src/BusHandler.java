import java.util.ArrayList;

public class BusHandler {
    private ArrayList<Bus> buses = new ArrayList<Bus>();

    public ArrayList<Bus> getBuses() {
        return buses;
    }

    public void setBuses(ArrayList<Bus> buses) {
        this.buses = buses;
    }

    public void addBus(Bus bus) {
        buses.add(bus);
    }

    public void printBuses() {
        int count = 1;
        for (Bus bus : buses) {
            System.out.println("Bus " + count + ":");
            System.out.println(bus);
            count++;
        }
    }
}


