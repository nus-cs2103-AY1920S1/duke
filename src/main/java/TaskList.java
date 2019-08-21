import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task t) throws DukeException {
        if (t.getTaskName().isBlank()) throw new EmptyTodoTextException();
        tasks.add(t);
        System.out.println("\t Got it. I've added this task: \n" +
                "\t  " + t.toString() + "\n" +
                "\t Now you have " + tasks.size() + " tasks in the list");
    }

    public void printTasks() {
        System.out.println("\t Here are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i++) {
            System.out.print("\t " + (i+1) + ".");
            System.out.println(tasks.get(i));
        }
    }

    public void markTaskAsCompleted(int taskNumber) {
        Task t = tasks.get(taskNumber - 1);
        t.markAsCompleted();

        System.out.println("\tNice! I've marked this task as done: \n" +
                "\t  [✓] " + t.getTaskName() + " " + t.getDetails() );
    }


}
