package duke;


import duke.exception.EmptyTodoTextException;
import duke.exception.TaskDoesNotExistException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * This class encapsulates methods and fields related to
 * direct operations on the list of <code>Task</code> objects
 */
public class TaskList {
    private List<Task> taskList;
    private UI ui;

    /**
     * Default constructor for <code>TaskList</code> class
     *
     * @param taskList A <code>List</code> object containing objects of type <code>Task</code>
     * @param ui       A <code>UI</code> object that handles UI output
     */
    public TaskList(List taskList, UI ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Alternative constructor for <code>TaskList</code> class
     * Creates a new <code>TaskList</code> instance
     *
     * @param storage A <code>Storage</code> object used to initialize a new <code>UI</code> instance
     */
    public TaskList(Storage storage) {
        this.taskList = new ArrayList<>();
        this.ui = new UI(this, storage);
    }


    /**
     * Adds task to <code>TaskList</code> object
     *
     * @param task         A <code>Task</code> instance to be added
     * @param printMessage A <code>boolean</code> which specifies whether a message should be
     *                     printed upon successful adding of task
     * @throws EmptyTodoTextException if taskName is blank
     */
    public void addTask(Task task, boolean printMessage) throws EmptyTodoTextException {
        if (task.getTaskName().isBlank()) throw new EmptyTodoTextException("The description of a todo cannot be empty");
        taskList.add(task);
        if (printMessage) ui.printAddTaskMessage(task);
    }

    /**
     * Prints tasks in the list
     */
    public void printTasks() {
        ui.printTasks();
    }

    /**
     * Marks a task as completed
     *
     * @param taskNumber   integer representing the number of the task to be marked as completed
     * @param printMessage A <code>boolean</code> which specifies whether a message should be
     *                     printed upon successful adding of task
     * @throws TaskDoesNotExistException if taskNumber does not correspond to task in list
     */
    public void markTaskAsCompleted(int taskNumber, boolean printMessage) throws TaskDoesNotExistException {
        if (taskNumber < 1 || taskNumber > taskList.size()) throw new TaskDoesNotExistException("Task not found");

        Task t = taskList.get(taskNumber - 1);
        t.markAsCompleted();
        if (printMessage) ui.printMarkTaskAsCompletedMessage(t);
    }

    /**
     * Deletes a task given its taskNumber
     *
     * @param taskNumber   integer representing the number of the task to be marked as completed
     * @param printMessage A <code>boolean</code> which specifies whether a message should be
     *                     printed upon successful adding of task
     * @throws TaskDoesNotExistException if taskNumber does not correspond to task in list
     */
    public void deleteTask(int taskNumber, boolean printMessage) throws TaskDoesNotExistException {
        if (taskNumber > taskList.size()) throw new TaskDoesNotExistException("Task not found");

        Task t = taskList.get(taskNumber - 1);
        taskList.remove(t);
        if (printMessage) ui.printDeleteTaskMessage(t);
    }

    /**
     * Gets list from <code>TaskList</code> object
     *
     * @return <code>List</code> object which contains list of tasks
     */
    public List<Task> getList() {
        return taskList;
    }


}
