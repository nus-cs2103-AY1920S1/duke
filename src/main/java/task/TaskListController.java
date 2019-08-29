package task;

import task.tasks.Task;

import java.util.List;
import java.util.stream.Collectors;

/***
 * <p>
 * Controller class that operates on user's task data.
 * </p>
 */
public class TaskListController {
    private List<Task> tasks;
    private TaskConsoleView view;

    /***
     * <p>
     * TaskListController constructor.
     * </p>
     * @param tasks list of tasks.
     */
    public TaskListController(List<Task> tasks) {
        this.tasks = tasks;
        view = new TaskConsoleView();
    }

    /***
     * <p>
     * Gets tasks.
     * </p>
     * @return list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /***
     * <p>
     * Adds tasks and prints corresponding feedback.
     * </p>
     * @param task task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        view.displayNewTask(task, tasks.size());
    }

    /***
     * <p>
     * Sets a task to done and prints corresponding feedback.
     * </p>
     * @param index index of task to be set to done.
     */
    public void setTaskToDone(int index) {
        tasks.get(index).setDone(true);
        view.displayTaskDone(tasks.get(index));
    }

    /***
     * <p>
     * Prints all tasks.
     * </p>
     */
    public void displayAllTasks() {
        view.displayAllTasks(tasks);
    }

    /***
     * <p>
     * Deletes a task and prints corresponding feedback.
     * </p>
     * @param index index of task to be deleted.
     */
    public void deleteTask(int index) {
        Task deletedTask = tasks.get(index);
        tasks.remove(index);
        view.displayTaskDeleted(deletedTask, tasks.size());

    }

    /***
     * <p>
     * Finds tasks containing a substring and prints corresponding feedback.
     * </p>
     * @param parameter substring to be searched.
     */
    public void findTasks(String parameter) {
        List<Task> matchingTasks = tasks.stream()
                .filter(task -> task.getDescription().contains(parameter))
                .collect(Collectors.toList());
        view.displayMatchingTasks(matchingTasks);
    }
}
