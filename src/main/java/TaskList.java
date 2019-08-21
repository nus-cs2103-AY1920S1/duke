import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> xs;

    public TaskList() {
        xs = new ArrayList<Task>();
    }

    public void addTask(Task t) {
        xs.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.printf("Now you have %d tasks in the list.\n", xs.size());
    }

    public void tickTask(int num) {
        xs.get(num - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(xs.get(num - 1));
    }

    public void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= xs.size(); i++) {
            System.out.println(i + ". " + xs.get(i - 1));
        }
    }
}
