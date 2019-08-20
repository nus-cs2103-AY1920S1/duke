import java.util.ArrayList;
import java.util.List;

public class ListOfInput {
    private List<Task> list;

    public ListOfInput() {
        list = new ArrayList<>();
    }

    public void addToList(String input) {
        Task task = new Task(input);
        list.add(task);
        print("    added: " + input);
    }

    public void markAsDone(int num) {
        Task task = list.get(num - 1);
        task.isDone();
        print("    Nice! I've marked this task as done:");
        print("    [" + task.getStatusIcon() + "] " + task.getDescription());
    }

    public void printList() {
        int i = 1;
        print("    Here are the tasks in your list:");
        for (Task task : list) {
            print("    " + i + ". [" + task.getStatusIcon() + "] "+ task.getDescription());
            i++;
        }
    }

    private void print(String x) {
        System.out.println(x);
    }
}
