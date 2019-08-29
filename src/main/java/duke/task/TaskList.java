package duke.task;

import java.util.List;
import java.util.ArrayList;

/**
 * A list of tasks, possibly to-dos, deadlines and events.
 */

public class TaskList {

    private List<Task> tasks;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a task list with the given list of tasks.
     *
     * @param tasks list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * @return size of this task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Adds the given task to the list.
     * @param task task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Marks the task of the given index as done.
     * @param index index of the task marked done.
     * @return the task of the given index.
     */
    public Task markDone(int index) {
        Task task = this.tasks.get(index - 1);
        task.setDone(true);
        return task;
    }

    /**
     * Deletes the task of the given index from the list.
     * @param index index of the task deleted.
     * @return the task being deleted.
     */
    public Task delete(int index) {
        Task task = this.tasks.get(index - 1);
        this.tasks.remove(index - 1);
        return task;
    }

    /**
     * @return a list of task names and descriptions of all tasks in the list.
     */
    public List<String> getTaskNames() {
        List<String> taskNames = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            taskNames.add((i + 1) + ". " + tasks.get(i));
        }
        return taskNames;
    }

    /**
     * @param keyword keyword that is used for finding tasks.
     * @return a list of task names and descriptions of tasks that contain this <code>keyword</code>.
     */
    public List<String> getTaskNamesIfMatch(String keyword) {
        List<String> taskNamesThatMatch = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskName().contains(keyword)) {
                taskNamesThatMatch.add((i + 1) + ". " + tasks.get(i));
            }
        }
        return taskNamesThatMatch;
    }

    /**
     * @return a list of simplified summaries of all tasks in the list.
     */
    public List<String> getSimplifiedTaskRepresentations() {
        List<String> simplifiedTaskRepresentations = new ArrayList<>();
        for (Task task : tasks) {
            simplifiedTaskRepresentations.add(task.getSimplifiedRepresentation());
        }
        return simplifiedTaskRepresentations;
    }
}
