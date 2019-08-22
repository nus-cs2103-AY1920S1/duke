import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public void addTask(Task task) {
        separator();
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        printTotalTask();
        separator();
    }

    public void markAsDone(int index) {
        separator();
        index--;
        tasks.get(index).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(index));
        separator();
    }

    public void printTasks() {
        separator();
        System.out.println("Here are the task in your list:");
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        separator();
    }

    public static void separator() {
        System.out.println("____________________________________________________________");
    }

    private void printTotalTask() {
        boolean isPlural = tasks.size() > 1;
        System.out.println("Now you have " + tasks.size() + " task"
                + (isPlural ? "s" : "") + " in the list.");
    }
}
