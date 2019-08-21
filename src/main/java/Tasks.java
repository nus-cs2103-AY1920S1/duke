import java.util.List;
import java.util.ArrayList;

public class Tasks {
    private List<Task> tasks;

    public Tasks() {
        tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
        System.out.println("     ------------------------------------------------------------");
        System.out.println("     Got it. I've added this task:");
        System.out.println("     \u2795  " + task.toString());
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("     ------------------------------------------------------------");
    }

    public void addTodo(String description) {
        Task task = new Todo(description);
        add(task);
    }

    public void addDeadline(String description, String by) {
        Task task = new Deadline(description, by);
        add(task);
    }

    public void addEvent(String description, String at) {
        Task task = new Event(description, at);
        add(task);
    }

    public void done(int arg) {
        int index = arg - 1;
        Task task = tasks.get(index);
        task.markAsDone();
        System.out.println("    ------------------------------------------------------------");
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + task);
        System.out.println("    ------------------------------------------------------------");
    }

    public void delete(int arg) {
        int index  = arg - 1;
        Task task = tasks.get(index);
        tasks.remove(index);
        System.out.println("    ------------------------------------------------------------");
        System.out.println("    Noted. I've removed this task:");
        System.out.println("    \uD83D\uDDD1  " + task.toString());
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("    ------------------------------------------------------------");
    }

    public void list() {
        int i = 1;
        System.out.println("    ============================================================");
        System.out.println("    Here are the tasks in your list:");
        System.out.println("    ------------------------------------------------------------");
        for(Task task : tasks) {
            System.out.println("     " + i + "." + task);
            i++;
        }
        System.out.println("    ============================================================");
    }
}
