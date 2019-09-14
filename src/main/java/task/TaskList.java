package task;

import java.util.List;
import java.util.ArrayList;

/**
 * The TaskList class defines the behaviour of a task list.
 * 
 * @author Joel Loong
 */
public class TaskList {

    private static ArrayList<Task> task;

    public TaskList() {
        TaskList.task = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> task) {
        TaskList.task = task;
    }

    /**
     * Adds task into the current list of tasks.
     * 
     * @param t Task to be added.
     * @return The string format of added task.
     */
    public static String addTask(Task t) {
        task.add(t);
        return Ui.printAddedTask(t);
    }

    /**
     * Deletes task into the current list of tasks.
     * 
     * @param index Index of task to be deleted.
     * @return The string format of deleted task.
     */
    public static String deleteTask(int index) {
        Task deletedTask = task.remove(index);
        return Ui.printDeleteTask(deletedTask);
    }

    /**
     * Marks the task that is in the list as done.
     * 
     * @param index Index of task to be marked as done.
     * @return The string format of task marked as done.
     */
    public static String doneTask(int index) {
        Task markAsDoneTask = task.get(index);
        markAsDoneTask.markAsDone();
        return Ui.printDoneTask(markAsDoneTask);
    }

    /**
     * Finds tasks with keyWord in the description.
     * 
     * @param keyWord Keyword to be found.
     * @return The string format of found tasks.
     */
    public static String findTask(String keyWord) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task t : TaskList.task) {
            if (t.getDescription().contains(keyWord)) {
                foundTasks.add(t);
            }
        }
        return Ui.printFoundTask(foundTasks);
    }

    public static ArrayList<Task> getTasks() {
        return TaskList.task;
    }

    public static int getCounter() {
        return TaskList.task.size();
    }
}