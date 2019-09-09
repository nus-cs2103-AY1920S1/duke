import java.util.ArrayList;

public class ListCommand extends Command {
    public ListCommand() {

    }

    public void execute(TaskList tl, Storage st) {
        ArrayList<Task> list = tl.list;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                System.out.println(i + 1 + "." + list.get(i));
            }
        }
    }
}
