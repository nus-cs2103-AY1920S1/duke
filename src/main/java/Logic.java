import java.util.ArrayList;
import java.util.List;

public class Logic {
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String SPACES = "     ";
    private static final String ADDED_MSG = SPACES + "added: ";

    private List<String> list;

    Logic() {
        list = new ArrayList<>();
    }

    int process(String command) {
        switch (command) {
            case BYE_COMMAND:
                return -1;
            case LIST_COMMAND:
                list();
                break;
            default:
                add(command);
        }
        return 0;
    }

    private void list() {
        for (int i = 0; i < list.size(); ++i) {
            System.out.println(SPACES + (i + 1) + ". " + list.get(i));
        }
    }

    private void add(String s) {
        list.add(s);
        System.out.println(ADDED_MSG + s);
    }

    private void print(String s) {
        System.out.println(SPACES + s);
    }
}
