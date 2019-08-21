import java.util.List;
import java.util.ArrayList;

public class Sheet {
    private List<Task> list = new ArrayList<>(100);
    public int numOfTask = list.size();

    public void add(Task task) {
        this.list.add(task);
    }

    public Task get(int index) {
        return this.list.get(index);
    }

    public void markAsDone(int num) {
        Task target = this.list.get(num - 1);
        this.list.set(num - 1,target.finish());
    }

    public void showList() {
        System.out.print(Formatter.LINE);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(Formatter.INDENT + (i + 1) + ". " +
                    list.get(i).toString());
        }
        System.out.println(Formatter.LINE);
    }

}
