import java.util.ArrayList;
import java.util.List;

public class ListOfInput {
    private List<String> list;

    public ListOfInput() {
        list = new ArrayList<>();
    }

    public void addToList(String input) {
        list.add(input);
        System.out.println("    added: " + input);
    }

    public void printList() {
        int i = 1;
        for (String task : list) {
            System.out.println("    " + i + ". " + task);
            i++;
        }
    }
}
