import java.util.LinkedList;

/**
 * To manage a list of task with several methods.
 *
 * @param null basic constructor with empty task.
 * @param tasks the list of current tasks that are loaded from a txt file.
  */
public class TaskList {
    private LinkedList<Task> tasks;

    public TaskList() {
        this.tasks = new LinkedList<Task>();
    }

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
