import java.util.ArrayList;
import java.util.List;

public class AddList {
    List<String> ls;

    public AddList() {
        ls = new ArrayList<String>();
    }
    public void printEvent() {
        int counter = 1;
        for(String a : ls) {
            System.out.println(counter + ". " + a);
            counter += 1;
        }
    }

    public void addEvent(String str) {
        ls.add(str);
    }
}
