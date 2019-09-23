import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    private static Task[] tasks;

    public TaskList(Task[] tasks) {
        this.tasks = tasks;
    }

    public Task[] getTasks() {
        return tasks;
    }

    /**
     * Add a task to the tasklist.
     * @param task task to be added.
     * @param index index of the tasklist array that the task will be added to.
     */
    public static void add(Task task, int index) {
        tasks[index] = task;
    }

    /**
     * Delete a task from the tasklist.
     * @param indextodel index of the tasklist that the task to be deleted is in
     * @return The deleted task.
     */
    public static Task delete(int indextodel) {
        ArrayList<Task> taskArrayList = new ArrayList<>(Arrays.asList(tasks));
        Task removed = taskArrayList.remove(indextodel - 1);

        tasks = new Task[100];
        for (int i = 0; i < taskArrayList.size(); i++) {
            tasks[i] = taskArrayList.get(i);
        }
        TaskList.updateTasks(tasks);

        return removed;
    }

    /**
     * Retrieves a specific task from the tasklist.
     * @param index index of the task to be retrieved.
     * @return the task retrieved.
     */
    public static Task get(int index) {
        return tasks[index];
    }

    /**
     * Updates the tasklist with a task array.
     * @param taskarr array of tasks to update the tasklist with.
     */
    public static void updateTasks(Task[] taskarr) {
        tasks = taskarr;
    }

    /**
     * This method just returns the number of current tasks saved.
     * @param arr this is the array of tasks that the code starts with (may not be empty).
     * @return number of tasks currently.
     */
    public static int initialiseNumOfTasks(Task[] arr) {
        int no = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                no++;
            } else {
                break;
            }
        }
        return no;
    }
}
