package task;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     A singleton that helps to manage the list of task and provides
 *     various operations on tasks.
 * </p>
 */
public class TaskList {
    private static List<Task> tasks;

    private TaskList() {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
    }

    /**
     * <p>
     *     Returns a new TaskList object that holds a reference to the
     *     working list.
     * </p>
     *
     * @return an instance of a TaskList object
     */
    public static TaskList newInstance() {
        return new TaskList();
    }

    public Task get(int idx) {
        return tasks.get(idx);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void clear() {
        tasks.clear();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Adds a new Todo Task to TaskList with the given task name and
     * done status.
     *
     * @param taskName The task name of the task.
     * @param isDone The done status of the newly added task.
     * @return The new Todo task.
     */
    public Task addNewTodoTask(String taskName, boolean isDone) {
        Task newTask = new Todo(taskName);
        if (isDone) {
            newTask.setDone();
        } else {
            newTask.setNotDone();
        }
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Adds a new Event Task to TaskList with the given task name, additional
     * info and done status.
     *
     * @param taskName The task name of the task.
     * @param additionalInfo The additional info of the Event task.
     * @param isDone The done status of the newly added task.
     * @return The new Event task.
     */
    public Task addNewEventTask(String taskName, String additionalInfo, boolean isDone) {
        Task newTask = new Event(taskName, additionalInfo);
        if (isDone) {
            newTask.setDone();
        } else {
            newTask.setNotDone();
        }
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Adds a new Deadline Task to TaskList with the given task name, additional
     * info and done status.
     *
     * @param taskName The task name of the task.
     * @param additionalInfo The additional info of the Deadline task in "DD/MM/YYYY HHmm"
     *                       format.
     * @param isDone The done status of the newly added task.
     * @return The new Deadline task.
     */
    public Task addNewDeadlineTask(String taskName, String additionalInfo, boolean isDone) {

        Task newTask = new Deadline(taskName, additionalInfo);
        if (isDone) {
            newTask.setDone();
        } else {
            newTask.setNotDone();
        }
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Deletes a task from task list by index.
     *
     * @param idx The index of the task in task list.
     * @return The deleted task.
     */
    public Task deleteTask(int idx) {
        Task task = tasks.remove(idx);
        return task;
    }

    /**
     * Marks a task from task list as done.
     *
     * @param idx The index of the task in task list.
     */
    public void markAsDone(int idx) {
        tasks.get(idx).setDone();
    }

    /**
     * Marks a task from task list as not done.
     *
     * @param idx The index of the task in task list.
     */
    void markAsNotDone(int idx) {
        tasks.get(idx).setNotDone();
    }

    /**
     * Finds all tasks whose task name or additional info contains the keyword.
     *
     * @param keyword The word or phrase to be searched for.
     * @return The list of tasks that are associated with the keyword.
     */
    public List<Task> generateListByKeyword(String keyword) {
        List<Task> findResult = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getName().contains(keyword)
                    || tasks.get(i).getAdditionalInfo().contains(keyword)) {
                findResult.add(tasks.get(i));
            }
        }
        return findResult;
    }

    @Override
    public boolean equals(Object obj) {
        return tasks.equals(((TaskList)obj).getTasks());
    }

}
