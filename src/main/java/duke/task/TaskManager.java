package duke.task;

import duke.exception.FailedToLoadIOException;
import duke.parser.FileToTaskParser;
import duke.parser.TaskToFileParser;
import duke.ui.Ui;

import java.util.Date;
import java.util.stream.Stream;

/**
 * This is the list of task items. This supports add, edit, delete operations. When a add, edit or delete operation is
 * to be performed, the <code>TaskManager</code> class provides an abstraction to support these operations.
 */
public class TaskManager {

    /**
     * This is the list of task items.
     */
    private TaskList taskList;

    private Schedule schedule;

    /**
     * Constructs a new list of tasks.
     */
    public TaskManager() {
        this.taskList = new TaskList();
        this.schedule = new Schedule();
    }

    /**
     * Constructs a new list of tasks from a stream of line. This stream of lines will be parsed by
     * {@link FileToTaskParser} to each individual tasks that will be added individually to form a list of tasks.
     * @param lines the stream of lines to be parsed.
     */
    public TaskManager(Stream<String> lines) {
        this.taskList = new TaskList();
        this.schedule = new Schedule();
        lines.forEach(line -> {
            try {
                Task task = FileToTaskParser.parse(line);
                addToTaskList(task);
                addToSchedule(task);
            } catch (FailedToLoadIOException lifpe) {
                new Ui().showLineError(lifpe.getLineCount(), line);
            }
        });
    }

    /**
     * Appends the task to the end of the list of the tasks.
     * @param task the task to be appended to the list
     */
    public void addToTaskList(Task task) {
        taskList.add(task);
    }

    public void addToSchedule(Task task) {
        schedule.add(task);
    }

    /**
     * Removes the task at the specified index from the list of tasks.
     * @param index the index of the task to be removed
     * @return a string representation of the task removed to be printed on a user interface.
     */
    public Task deleteFromTaskList(int index) {
        return taskList.delete(index - 1);
    }

    public void deleteFromSchedule(Task task) {
        schedule.delete(task);
    }
    /**
     * Marks the task at the specified index from the list of tasks as done.
     * @param index the index of the task to be done.
     * @return a string representation of the done task to be be printed on a user interface.
     */
    public String markTaskAsDone(int index) {
        return taskList.done(index).toString();
    }

    /**
     * Returns the number of tasks in the list.
     * @return the number of tasks in the list.
     */
    public int getTaskListSize() {
        return taskList.size();
    }

    /**
     * Returns a string representation of the list of tasks.
     * @return a string representation of the list of tasks
     */
    public String showTaskList() {
        return taskList.view();
    }

    public String showSchedule(String date) {
        return date.isBlank() ? schedule.view() : schedule.view(date);
    }

    /**
     * Finds all matching tasks with the given keyword.
     * @param keyword the keyword to look out for in tasks' description
     * @return a string representation of the list of tasks that match the keyword
     */
    public String findFromTaskList(String keyword) {
        return taskList.find(keyword);
    }

    /** Returns a stream of string to be saved into the {@link duke.storage.Storage}. Each task is parsed using a task
     * parser. See {@link TaskToFileParser} for more information.
     * @return a stream of string to be saved into storage
     */
    public Stream<String> getCurrentTaskListToSave() {
        return taskList.save();
    }


    public String remind(int index, Date date) {
        return taskList.remind(index - 1, date).toString();
    }
}
