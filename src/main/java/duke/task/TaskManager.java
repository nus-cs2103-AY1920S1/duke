package duke.task;

import duke.exception.FailedToLoadIoException;
import duke.parser.FileToTaskParser;
import duke.parser.TaskToFileParser;
import duke.ui.Ui;

import java.util.Date;
import java.util.stream.Stream;

/**
 * This is the task manager for the duke program. The duke program has a task list as well as a schedule. The task
 * manager provides abstraction to both components. This is also to help provide flexibility when more tasks list is
 * added.
 */
public class TaskManager {

    /**
     * This is the list of task items.
     */
    private TaskList taskList;

    /**
     * This is the schedule of task items.
     */
    private Schedule schedule;

    /**
     * Constructs a new task manager to manage task list and schedule.
     */
    public TaskManager() {
        this.taskList = new TaskList();
        this.schedule = new Schedule();
    }

    /**
     * Constructs a new task manager. This stream of lines will be parsed by {@link FileToTaskParser} to each
     * individual tasks that will be added individually to both the task list and the schedule.
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
            } catch (FailedToLoadIoException lifpe) {
                new Ui().showLineError(lifpe.getLineCount(), line);
            }
        });
    }

    /**
     * Appends the task to the end of the task list of the tasks.
     * @param task the task to be appended to the list
     */
    public void addToTaskList(Task task) {
        assert (task != null);
        taskList.add(task);
    }

    /**
     * Inserts the task to the end of a sorted schedule of the tasks.
     * @param task the task to be inserted into the schedule
     */
    public void addToSchedule(Task task) {
        assert (task != null);
        schedule.add(task);
    }

    /**
     * Removes the task at the specified index from the task list.
     * @param index the index of the task to be removed
     * @return the task deleted from the task list
     */
    public Task deleteFromTaskList(int index) {
         assert (index >= 1);
        return taskList.delete(index - 1);
    }

    /**
     * Search and delete the task from the schedule.
     * @param task the task to be deleted
     */
    public void deleteFromSchedule(Task task) {
        assert (task != null);
        schedule.delete(task);
    }

    /**
     * Marks the task at the specified index from the list of tasks as done.
     * @param index the index of the task to be done.
     * @return a string representation of the done task to be be printed on a user interface.
     */
    public String markTaskAsDone(int index) {
        assert (index >= 1);
        return taskList.done(index - 1).toString();
    }

    /**
     * Returns the number of tasks in the task list.
     * @return the number of tasks in the task list.
     */
    public int getTaskListSize() {
        return taskList.size();
    }

    /**
     * Returns a string representation of the task list.
     * @return a string representation of the task list
     */
    public String showTaskList() {
        return taskList.view();
    }

    /**
     * Retirm a string representation fo the schedule of task at a specified date.
     * @param date specifies a particular day of the schedule of tasks to be displayed
     * @return a string representation of the schedule of tasks
     */
    public String showSchedule(String date) {
        return date.isBlank() ? schedule.view() : schedule.view(date);
    }

    /**
     * Finds all matching tasks with the given keyword.
     * @param keyword the keyword to look out for in tasks' description
     * @return a string representation of the task list that match the keyword
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

    /**
     * Add reminder to the specified index from the task list.
     * @param index the index of the task in the task list
     * @param date the date to set the reminder at
     * @return a string representation of the task in which the reminder was set
     */
    public String remind(int index, Date date) {
        assert (date != null);
        return taskList.remind(index - 1, date);
    }

}
