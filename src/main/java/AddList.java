import java.util.ArrayList;
import java.util.List;

public class AddList {
    List<String> ls1;
    List<String> ls2;

    public AddList() {
        ls1 = new ArrayList<String>();
        ls2 = new ArrayList<String>();
    }
    public void printAllEvent() {
        int counter = 1;
        for(String a : ls1) {
            System.out.println("[" + ls2.get(counter - 1) + "]" + " "+ (counter) + ". " + a);
            counter += 1;
        }
    }

    public void printEvent(int index) {
        System.out.println("   [" + ls2.get(index - 1) + "] " + ls1.get(index));
    }

    public void addEvent(String str) {
        ls1.add(str);
        ls2.add("✗");
    }

    public void changeEvent(int index) {
        ls2.set(index,"✓");
    }
}
