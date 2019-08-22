import java.util.ArrayList;
import java.util.List;

public class AddList {
    List<Mission> ls;
    int counter = 0;
    public AddList() {
        ls = new ArrayList<Mission>();
    }
    public void printAllEvent() {
        int counter = 1;
        for(Mission a : ls) {
            System.out.print(counter + ". ");
            ls.get(counter - 1).printMission();
            counter += 1;
        }
    }

    public void printEvent(int index) {
        ls.get(index).printMission();
    }

    public void addEventWithTime(String str, String type, String time, String atby) {
        ls.add(new Mission(str, type, time, atby));
        counter += 1;
    }

    public void addEventWithoutTime(String str, String type) {
        ls.add(new Mission(str, type));
        counter += 1;
    }

    public void changeEvent(int index) {
        ls.get(index).changeState();
    }
}
