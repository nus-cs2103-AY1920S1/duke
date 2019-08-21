import java.util.List;
import java.util.ArrayList;

public class Sheet {
    private List<Task> list = new ArrayList<>(100);

    public void add(Task task) {
        this.list.add(task);
    }

    public void showList() {
        System.out.print(Formatter.LINE);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(Formatter.INDENT + (i + 1) + ". [" +
                    list.get(i).getStatusIcon() + "] " + list.get(i).getStatusIcon());
        }
        System.out.println(Formatter.LINE);
    }

}
