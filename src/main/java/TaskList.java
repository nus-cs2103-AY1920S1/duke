import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task t) throws DukeException {
        if (t.getTaskName().isBlank()) throw new EmptyTodoTextException("The description of a todo cannot be empty");
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

    public void markTaskAsCompleted(int taskNumber) throws DukeException {
        if (taskNumber < 1 || taskNumber > tasks.size()) throw new TaskDoesNotExistException("Task not found");
        Task t = tasks.get(taskNumber - 1);

        t.markAsCompleted();
        System.out.println("\t Nice! I've marked this task as done: \n" +
                "\t  [✓] " + t.getTaskName() + " " + t.getDetails() );
    }

    public void deleteTask(int taskNumber) {
        Task t = tasks.get(taskNumber - 1);
        tasks.remove(t);

        System.out.println("\t I've removed this task: \n" +
                "\t  [✓] " + t.getTaskName() + " " + t.getDetails() + "\n" +
                "\t Now you have " + tasks.size() + " tasks in the list");
    }


}
