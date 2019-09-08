package duke.task;

import duke.task.tasks.Task;

import java.util.List;
import java.util.stream.Collectors;

/***
 * <p>
 * Controller class that operates on user's duke.task data.
 * </p>
 */
public class TasksController {
    private List<Task> tasks;
    private TasksView view;

    /***
     * <p>
     * TaskListController constructor.
     * </p>
     * @param tasks list of tasks.
     */
    public TasksController(List<Task> tasks, TasksView view) {
        this.tasks = tasks;
        this.view = view;
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
     * @param task duke.task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        view.displayNewTask(task, tasks.size());
    }

    /***
     * <p>
     * Sets a duke.task to done and prints corresponding feedback.
     * </p>
     * @param index index of duke.task to be set to done.
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
        try {
            view.displayAllTasks(tasks);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * <p>
     * Deletes a duke.task and prints corresponding feedback.
     * </p>
     * @param index index of duke.task to be deleted.
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
        view.displaySearchResults(matchingTasks);
    }
}
