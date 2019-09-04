package task;

import java.util.List;
import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> task;

    protected TaskList() {
        TaskList.task = new ArrayList<>();
    }

    protected TaskList(ArrayList<Task> task) {
        TaskList.task = task;
    }

    /**
     * Adds task into the current list of tasks.
     * 
     * @param t Task to be added.
     */
    protected static String addTask(Task t) {
        task.add(t);
        return Ui.printAddedTask(t);
    }

    /**
     * Deletes task into the current list of tasks.
     * 
     * @param index Index of task to be deleted.
     */
    protected static String deleteTask(int index) {
        Task deletedTask = task.remove(index);
        return Ui.printDeleteTask(deletedTask);
    }

    /**
     * Mark the task that is in the list as done.
     * 
     * @param index Index of task to be marked as done.
     */
    protected static String doneTask(int index) {
        Task markAsDoneTask = task.get(index);
        markAsDoneTask.markAsDone();
        return Ui.printDoneTask(markAsDoneTask);
    }

    protected static String findTask(String keyWord) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task t : TaskList.task) {
            if (t.getDescription().contains(keyWord)) {
                foundTasks.add(t);
            }
        }
        return Ui.printFoundTask(foundTasks);
    }

    protected static ArrayList<Task> getTasks() {
        return TaskList.task;
    }

    protected static int getCounter() {
        return TaskList.task.size();
    }
}