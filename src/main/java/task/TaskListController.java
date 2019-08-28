package task;

import task.tasks.Task;

import java.util.List;

/***
 * Controller class that operates on user's task data.
 */
public class TaskListController {
    private List<Task> tasks;
    private TaskListView view;

    /***
     * TaskListController constructor.
     * @param tasks list of tasks.
     */
    public TaskListController(List<Task> tasks) {
        this.tasks = tasks;
        view = new TaskListView();
    }

    /***
     * Gets tasks.
     * @return list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /***
     * Adds tasks and prints corresponding feedback.
     * @param task task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        view.displayNewTask(task, tasks.size());
    }

    /***
     * Sets a task to done and prints corresponding feedback.
     * @param index index of task to be set to done.
     */
    public void setTaskToDone(int index) {
        tasks.get(index).setDone(true);
        view.displayTaskDone(tasks.get(index));
    }

    /***
     * Prints all tasks.
     */
    public void displayAllTasks() {
        view.displayAllTasks(tasks);
    }

    /***
     * Deletes a task and prints corresponding feedback.
     * @param index index of task to be deleted.
     */
    public void deleteTask(int index) {
        Task deletedTask = tasks.get(index);
        tasks.remove(index);
        view.displayTaskDeleted(deletedTask, tasks.size());

    }
}
