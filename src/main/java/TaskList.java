import java.util.ArrayList;

/**
 * Contains all tasks added in by the user.
 */
public class TaskList {
    private static ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds task into current tasklist.
     *
     * @param task Task to be added into tasklist
     */
    public static void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes task from current tasklist.
     *
     * @param taskNum Task number of task to be deleted from tasklist
     */
    public static void delTask(int taskNum) {
        taskList.remove(taskNum - 1);
    }

    /**
     * Returns the current number of tasks in tasklist.
     *
     * @return number of tasks in tasklist
     */
    public static int getTaskListSize() {
        return taskList.size();
    }

    public static Task getTaskAt(int taskNum) {
        return taskList.get(taskNum - 1);
    }

    public static ArrayList<Task> getCurrentTasks() {
        return taskList;
    }

    public static void removeAllTasks() {
        taskList = new ArrayList<Task>();
    }

    /**
     * Returns all the tasks currently in the tasklist.
     *
     * @return all tasks currently in tasklist
     */
    public static String printTasks() {
        if (taskList.isEmpty()) {
            return "task list is empty";
        } else {
            String output = "Here are the tasks in your list:\n";
            for (int i = 0; i < taskList.size(); i++) {
                output += (i + 1) + "." + taskList.get(i) + "\n";
            }
            return output;
        }
    }

    /**
     * Search in a tasklist for the tasks that matches the term provided.
     *
     * @param term Used to search for tasks that matches the term
     */
    public static String findTasks(String term) {
        ArrayList<Task> searchedTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().toLowerCase().contains(term.toLowerCase())) {
                searchedTasks.add(task);
            }
        }
        if (searchedTasks.isEmpty()) {
            return "There is no tasks";
        } else {
            String output = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < searchedTasks.size(); i++) {
                output += (i + 1) + "." + searchedTasks.get(i) + "\n";
            }
            return output;
        }
    }
}
