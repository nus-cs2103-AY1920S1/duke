package duke.task;

import duke.exception.EmptyTaskTextException;
import duke.exception.TaskDoesNotExistException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * This class encapsulates methods and fields related to
 * direct operations on the list of <code>Task</code> objects.
 */
public class TaskList {
    private List<Task> taskList;

    /**
     * Default constructor for <code>TaskList</code> class.
     *
     * @param taskList A <code>List</code> object containing objects of type <code>Task</code>
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Alternative constructor for <code>TaskList</code> class
     * Creates a new <code>TaskList</code> instance.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }


    /**
     * Adds task to <code>TaskList</code> object.
     *
     * @param task A <code>Task</code> instance to be added
     * @throws EmptyTaskTextException if taskName is blank
     */
    public void addTask(Task task) throws EmptyTaskTextException {
        if (task.getTaskName().isBlank()) {
            throw new EmptyTaskTextException("Task name cannot be empty");
        }
        taskList.add(task);
        assert taskList.size() > 0;
    }

    /**
     * Marks a task as completed.
     *
     * @param taskNumber integer representing the number of the task to be marked as completed
     *
     * @return <code>Task</code> that was marked as completed
     *
     * @throws TaskDoesNotExistException if taskNumber does not correspond to task in list
     */
    public Task markTaskAsCompleted(int taskNumber) throws TaskDoesNotExistException {
        if (taskNumber < 1 || taskNumber > taskList.size()) {
            throw new TaskDoesNotExistException("Task not found");
        }

        Task task = taskList.get(taskNumber - 1);
        task.markAsCompleted();

        assert task.isCompleted;

        return task;
    }

    /**
     * Deletes a task given its taskNumber.
     * @param taskNumber integer representing the number of the task to be marked as completed
     *
     * @return <code>Task</code> that was deleted
     *
     * @throws TaskDoesNotExistException if taskNumber does not correspond to task in list
     */
    public Task deleteTask(int taskNumber) throws TaskDoesNotExistException {
        if (taskNumber > taskList.size()) {
            throw new TaskDoesNotExistException("Task not found");
        }

        Task task = taskList.get(taskNumber - 1);
        taskList.remove(task);

        assert !taskList.contains(task);

        return task;
    }

    /**
     * Gets search results list from <code>TaskList</code> object.
     *
     * @return <code>List</code> object which contains list matching search results
     */

    public List<Task> findMatchingTasks(String searchTerm) {
        List<Task> searchResults = new ArrayList<>();
        assert taskList != null;
        for (Task task : taskList) {
            if (task.getTaskName().toLowerCase().contains(searchTerm.toLowerCase())) {
                searchResults.add(task);
            }
        }
        return searchResults;
    }

    /**
     * Gets list of tasks.
     *
     * @return <code>List</code> object which contains all tasks
     */
    public List<Task> getList() {
        assert taskList != null;
        return taskList;
    }


    /**
     * Gives a task a hash tag.
     * @param taskNumber integer representing the number of the task to be marked as completed
     * @param tag <code>String</code> representing the hash tag without the # symbol.
     * @return <code>Task</code> that was successfully tagged
     *
     * @throws TaskDoesNotExistException if taskNumber does not correspond to task in list
     */
    public Task tagTask(int taskNumber, String tag) throws TaskDoesNotExistException {
        if (taskNumber > taskList.size()) {
            throw new TaskDoesNotExistException("Task not found");
        }

        Task task = taskList.get(taskNumber - 1);
        task.tag = "#" + tag;

        return task;
    }
}
