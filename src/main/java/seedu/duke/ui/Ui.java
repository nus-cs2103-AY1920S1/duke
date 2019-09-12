package seedu.duke.ui;

import seedu.duke.statistic.Statistic;
import seedu.duke.task.Event;
import seedu.duke.task.Deadline;
import seedu.duke.task.Task;
import seedu.duke.task.Todo;

import seedu.duke.tasklist.TaskList;

/**
 * Ui class is responsible of printing UI related Strings, such as the Welcome String.
 */
public abstract class Ui {
    /**
     * Default constructor but the abstract class will never be instantiated.
     */
    public Ui(){
    }

    /**
     * Returns list sequence for LIST command.
     *
     * @param tasks TaskList object.
     * @return String.
     */
    public abstract String getListSequence(TaskList tasks);

    /**
     * Returns bye sequence for BYE command.
     *
     * @return String.
     */
    public abstract String getByeSequence();

    /**
     * Returns find sequence for FIND command.
     *
     * @param tasks TaskList object.
     * @return String.
     */
    public abstract String getFoundTasks(TaskList tasks);

    /**
     * Returns delete sequence for DELETE command.
     *
     * @param tasks TaskList object.
     * @param taskToDelete Task object.
     * @return String.
     */
    public abstract String getDeleteSequence(TaskList tasks, Task taskToDelete);

    /**
     * Returns event sequence for EVENT command.
     *
     * @param tasks TaskList object.
     * @param newEvent Event object.
     * @return String.
     */
    public abstract String getEventSequence(TaskList tasks, Event newEvent);

    /**
     * Returns todo sequence for TODO command.
     *
     * @param tasks TaskList object.
     * @param newTodo Todo object.
     * @return String.
     */
    public abstract String getTodoSequence(TaskList tasks, Todo newTodo);

    /**
     * Returns deadline sequence for DEADLINE command.
     *
     * @param tasks TaskList object.
     * @param newDeadline deadline object.
     * @return String.
     */
    public abstract String getDeadlineSequence(TaskList tasks, Deadline newDeadline);

    /**
     * Returns done sequence for DONE command.
     *
     * @param tasks TaskList object.
     * @param taskNum int Index of the specified task.
     * @return String.
     */
    public abstract String getDoneSequence(TaskList tasks, int taskNum);

    public abstract String getResetStatSequence(Statistic stats);

    public abstract String getAllStatSequence(Statistic stats);

    public abstract String getCompletedEventStatSequence(Statistic stat, TaskList tasks);

}
