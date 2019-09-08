package seedu.duke.ui;

import seedu.duke.DukeException;

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
     * Default constructor but an abstract class will never be instantiated.
     */
    public Ui(){
    }

    /**
     * Returns the error String prompt.
     *
     * @param e DukeException object, which is a subclass of the Exception class.
     */
    public static String getLoadingError(DukeException e) {
        return (e.getMessage());
    }

    public abstract String getListSequence(TaskList tasks);
    public abstract String getByeSequence();
    public abstract String getFoundTasks(TaskList tasks);
    public abstract String getDeleteSequence(TaskList tasks, Task taskToDelete);
    public abstract String getEventSequence(TaskList tasks, Event newEvent);
    public abstract String getTodoSequence(TaskList tasks, Todo newTodo);
    public abstract String getDeadlineSequence(TaskList tasks, Deadline newDeadline);
    public abstract String getDoneSequence(TaskList tasks, int taskNum);

}
