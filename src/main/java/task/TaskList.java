package task;

import exception.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A singleton that helps to manage the list of tasks and provides
 * various operations on tasks.
 */
public class TaskList {
    private static List<Task> tasks;

    private TaskList() {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
    }

    /**
     * Returns a new TaskList object that holds a reference to the
     * working list.
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
     * @param isDone   The done status of the newly added task.
     * @return The new Todo task.
     */
    public Task addNewTodoTask(String taskName, boolean isDone) {
        Task newTask = new Todo(taskName);
        setDoneStatus(newTask, isDone);
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Adds a new Event Task to TaskList with the given task name, additional
     * info and done status.
     *
     * @param taskName       The task name of the task.
     * @param additionalInfo The additional info of the Event task.
     * @param isDone         The done status of the newly added task.
     * @return The new Event task.
     */
    public Task addNewEventTask(String taskName, String additionalInfo, boolean isDone) {
        assert !additionalInfo.isEmpty() : "Additional info of Event task cannot be empty.";
        Task newTask = new Event(taskName, additionalInfo);
        setDoneStatus(newTask, isDone);
        tasks.add(newTask);
        return newTask;
    }


    /**
     * Adds a new Deadline Task to TaskList with the given task name, additional
     * info and done status.
     *
     * @param taskName       The task name of the task.
     * @param additionalInfo The additional info of the Deadline task in "DD/MM/YYYY HHmm"
     *                       format.
     * @param isDone         The done status of the newly added task.
     * @return The new Deadline task.
     */
    public Task addNewDeadlineTask(String taskName, String additionalInfo, boolean isDone) {
        assert !additionalInfo.isEmpty() : "Additional info of Deadline task cannot be empty.";
        Task newTask = new Deadline(taskName, additionalInfo);
        setDoneStatus(newTask, isDone);
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
     * Deletes a task from task list by index.
     *
     * @param idx The index of the task in task list.
     * @return The deleted task.
     */
    public void insertTask(Task task, int idx) {
        tasks.add(idx, task);
    }

    /**
     * Marks a task from task list as done.
     *
     * @param idx The index of the task in task list.
     */
    public void markAsDone(int idx) throws InvalidArgumentException {
        if (tasks.get(idx).getStatus() == Task.DONE) {
            throw new InvalidArgumentException(
                    String.format("Task %d is already done.", idx + 1));
        }
        tasks.get(idx).setDone();
    }

    /**
     * Marks a task from task list as not done.
     *
     * @param idx The index of the task in task list.
     */
    public void markAsNotDone(int idx) {
        if (tasks.get(idx).getStatus() == Task.NOT_DONE) {
            throw new InvalidArgumentException(
                    String.format("Task %d is already not done.", idx + 1));
        }
        tasks.get(idx).setNotDone();
    }

    /**
     * Finds all tasks whose task name or additional info contains the keyword.
     *
     * @param keyword The word or phrase to be searched for.
     * @return The list of tasks that are associated with the keyword.
     */
    public List<Task> generateListByKeyword(String keyword) {
        assert !keyword.isEmpty() : "Find keyword cannot be empty";
        List<Task> findResult = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isAssociated(keyword)) {
                findResult.add(task);
            }
        }
        return findResult;
    }

    public boolean sort(int sortCategory, boolean isReversed) {
        Comparator<Task> comparator;
        switch (sortCategory) {
        case Task.NAME_CATEGORY:
            comparator = TaskComparatorFactory.getNameComparator(isReversed);
            break;

        case Task.DEADLINE_CATEGORY:
            comparator = TaskComparatorFactory.getDeadlineComparator(isReversed);
            break;

        case Task.TYPE_CATEGORY:
            comparator = TaskComparatorFactory.getTypeComparator(isReversed);
            break;

        case Task.STATUS_CATEGORY:
            comparator = TaskComparatorFactory.getStatusComparator(isReversed);
            break;

        default:
            return false;
        }
        tasks.sort(comparator);
        return true;
    }

    /**
     * Marks the status of a given task as done or not depending on the isDone argument.
     *
     * @param newTask The task whose status needs to be fixed.
     * @param isDone Whether the task is already done or not.
     */
    private void setDoneStatus(Task newTask, boolean isDone) {
        if (isDone) {
            newTask.setDone();
        } else {
            newTask.setNotDone();
        }
    }


    @Override
    public boolean equals(Object obj) {
        return tasks.equals(((TaskList) obj).getTasks());
    }

}
