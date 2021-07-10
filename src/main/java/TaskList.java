import java.util.LinkedList;

public class TaskList {
    private LinkedList<Task> tasks;

    /**
     * Default constructor. Create an empty task.
     */
    public TaskList() {
        this.tasks = new LinkedList<Task>();
    }

    /**
     * To manage a list of task with several methods.
     *
     * @param tasks the list of current tasks that are loaded from a txt file.
     */
    public TaskList(LinkedList<Task> tasks) {
        this.tasks = tasks;
    }

    public LinkedList<Task> getListOfTasks() {
        return tasks;
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    public void deleteTask(int taskIndex) {
        tasks.remove(taskIndex);
    }
}
