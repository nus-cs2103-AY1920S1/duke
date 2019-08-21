import java.util.List;
import java.util.ArrayList;

public class Adder {
    private List<String> list = new ArrayList<>(100);

    public void add(String str) {
        this.list.add(str);
    }

    public void showList() {
        System.out.print(Formatter.LINE);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(Formatter.INDENT + (i + 1) + ". " + list.get(i));
        }
        System.out.println(Formatter.LINE);
    }

}
