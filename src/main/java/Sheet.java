import java.util.List;
import java.util.ArrayList;

public class Sheet {
    private List<Task> list = new ArrayList<>(100);
    public int numOfTask = 0;

    public void add(Task task) {
        this.list.add(task);
        System.out.print(Formatter.LINE + Formatter.INDENT + Formatter.GOT + Formatter.INDENT + "  "
                + task.toString() + "\n");
        this.numOfTask++;
        this.count();
        System.out.printf(Formatter.LINE);
    }

    private void count() {
        if (this.list.size() < 2) {
            System.out.printf(Formatter.INDENT + "Now you have %d task in the list.\n", numOfTask);
        } else {
            System.out.printf(Formatter.INDENT + "Now you have %d tasks in the list.\n", numOfTask);
        }
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public Task get(int index) {
        return this.list.get(index);
    }

    public void markAsDone(int num) {
        Task target = this.list.get(num - 1);
        this.list.set(num - 1,target.finish());
        System.out.printf(Formatter.LINE + Formatter.INDENT + Formatter.DONE + Formatter.INDENT +
                this.list.get(num - 1).toString() + "\n" + Formatter.LINE);
    }

    public void showList() {
        System.out.print(Formatter.LINE + Formatter.INDENT + Formatter.LIST);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(Formatter.INDENT + (i + 1) + ". " +
                    list.get(i).toString());
        }
        System.out.printf(Formatter.LINE);
    }

}
