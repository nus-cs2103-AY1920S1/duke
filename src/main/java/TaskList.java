import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks = new ArrayList<>();


    public void addTask(String taskName) {
        int id = tasks.size() + 1;
        tasks.add(new Task(id, taskName));
        OutputUtilities.printLine();
        System.out.println("added: " + taskName);
        OutputUtilities.printLine();
    }

    public void printTasks() {
        OutputUtilities.printLine();
        tasks.forEach(System.out::println);
        OutputUtilities.printLine();
    }

    public void markTaskAsCompleted(int id) {
        Task t = tasks.get(id - 1);
        t.markAsCompleted();

        OutputUtilities.printLine();
        System.out.println("\tNice! I've marked this task as done: \n" +
                "\t  [✓] " + t.getTaskName());
        OutputUtilities.printLine();
    }


}
