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
    public void list() {
        int counter = 1;

        //list out all the texts from the user
        System.out.println("\tHere are the tasks in your list:");
        for (Task t: tasks) {
            System.out.println("\t" + counter + "." + t.toString());
            counter++;
        }
    }

    /**
     * @return Number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * @return List of tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
}