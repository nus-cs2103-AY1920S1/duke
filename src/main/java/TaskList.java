import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    TaskList() {

    }

    public int getSize() {
        // return the size
    }

    public void delete(Task task) {
        // delete the task
    }

    public void list() {
        // prints a list of all tasks stored in this tasklist
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
    }
}
