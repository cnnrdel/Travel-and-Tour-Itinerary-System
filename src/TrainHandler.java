import java.util.ArrayList;

public class TrainHandler {
    private ArrayList<Train> trainList = new ArrayList<Train>();

    public void addTrain(Train t) {
        trainList.add(t);
    }

    public void displayTrainList() {
        for (Train train : trainList) {
            System.out.println(train);
        }
    }

    public ArrayList<Train> getTrainList() {
        return trainList;
    }
}
