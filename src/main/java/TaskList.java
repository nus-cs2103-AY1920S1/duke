import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList = new ArrayList<>();

    public void addTask(Task t) throws DukeException {
        if (t.getTaskName().isBlank()) throw new EmptyTodoTextException("The description of a todo cannot be empty");
        taskList.add(t);
        System.out.println("\t Got it. I've added this task: \n" +
                "\t  " + t.toString() + "\n" +
                "\t Now you have " + taskList.size() + " tasks in the list");
    }

    public void printTasks() {
        System.out.println("\t Here are the tasks in your list:");
        for(int i = 0; i < taskList.size(); i++) {
            System.out.print("\t " + (i+1) + ".");
            System.out.println(taskList.get(i));
        }
    }

    public void markTaskAsCompleted(int taskNumber) throws DukeException {
        if (taskNumber < 1 || taskNumber > taskList.size()) throw new TaskDoesNotExistException("Task not found");
        Task t = taskList.get(taskNumber - 1);

        t.markAsCompleted();
        System.out.println("\t Nice! I've marked this task as done: \n" +
                "\t  [✓] " + t.getTaskName() + " " + t.getDetails() );
    }

    public void deleteTask(int taskNumber) {
        Task t = taskList.get(taskNumber - 1);
        taskList.remove(t);

        System.out.println("\t I've removed this task: \n" +
                "\t  [✓] " + t.getTaskName() + " " + t.getDetails() + "\n" +
                "\t Now you have " + taskList.size() + " tasks in the list");
    }

    public List<Task> getList() {
        return taskList;
    }
}
