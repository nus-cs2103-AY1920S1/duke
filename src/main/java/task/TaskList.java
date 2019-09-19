package task;

import java.util.ArrayList;

/**
 * List of user-specified tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs TaskList object with empty list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }


    /**
     * Constructs TaskList object with existing list of tasks.
     * @param tasks List of task.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Removes task from the list according to its index.
     * @param index Task index.
     * @return Removed task.
     */
    public Task remove(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        return task;
    }

    /**
     * Marks specific task in the list as done according to its index.
     * @param index Task index.
     * @return Done task.
     */
    public Task done(int index) {
        Task task = tasks.get(index);
        task.markDone();
        return task;
    }

    /**
     * Adds task into the list.
     * @param task Specified task.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Lists all the tasks in the list.
     */
    public String list() {
        int counter = 1;
        StringBuilder str = new StringBuilder();

        //list out all the texts from the user
        for (Task t: tasks) {
            str.append(counter).append(".").append(t.toString()).append("\n");
            counter++;
        }

        return str.toString();
    }

    /**
     * Lists all the tasks in the list provided by the user.
     * @param taskList List of tasks.
     */
    public String list(ArrayList<Task> taskList) {
        int counter = 1;
        StringBuilder str = new StringBuilder();

        //list according to given list
        for (Task t: taskList) {
            str.append(counter).append(".").append(t.toString()).append("\n");
            counter++;
        }

        return str.toString();
    }

    /**
     * Finds specific tasks in the existing list of tasks.
     * @param keyword Keyword to search in description.
     * @return List of tasks according to the keyword.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> taskList = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getDescription().contains(keyword)) {
                taskList.add(task);
            }
        }
        return taskList;
    }

    /**
     * Returns the total number of tasks in the list.
     * @return Number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     * @return List of tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Checks for duplicate tasks.
     * @param task Task to be checked.
     * @return True for duplicate.
     */
    public boolean isDuplicateTask(Task task) {
        boolean isDup = false;
        String description = task.getDescription().toLowerCase();

        for (Task t: tasks) {
            if (description.equals(t.getDescription().toLowerCase())) {
                isDup = true;
                break;
            }
        }

        return isDup;
    }
}