package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Ui;
import duke.util.UiMessage;
import duke.util.exception.DukeException;
import duke.util.exception.ExceptionType;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Represents a command to add a new task to the list of tasks.
 */
public abstract class AddTaskCommand implements Command {
    Scanner s;
    private String description;
    private String deadlineString;

    AddTaskCommand(String fullCommand) {
        super();
        this.s = new Scanner(fullCommand);
        s.next(); // ignore command
    }

    /**
     * Returns the description of the task to be added.
     * @return Description of the task to be added.
     */
    String getDescription() {
        return this.description;
    }

    /**
     * Returns the string representing the deadline of the task to be added.
     * For Deadline tasks, this refers to the expected date of completion.
     * For Event tasks, this refers to the expected date of occurrence.
     * @return Deadline of the task to be added.
     */
    String getDeadlineString() {
        return this.deadlineString;
    }

    /**
     * Retrieves the description of the task to be added, based on command issued by user.
     * @throws DukeException Application-specific exception thrown during execution.
     */
    void setDescription() throws DukeException {
        try {
            this.description = this.s.next().strip();
        } catch (NoSuchElementException e) {
            // user input after task type is blank
            throw new DukeException(ExceptionType.DESCRIPTION_BLANK);
        }
    }

    /**
     * Retrieves the string representing the deadline of the task to be added,
     * based on command issued by user.
     * @throws DukeException Application-specific exception thrown during execution.
     */
    void setDeadlineString() throws DukeException {
        try {
            this.deadlineString = this.s.next().strip();
        } catch (NoSuchElementException e) {
            // no deadline entered
            throw new DukeException(ExceptionType.DEADLINE_BLANK);
        }
    }

    /**
     * Creates the task to be added, based on command issued by the user.
     * @return Task to be added.
     */
    abstract Task createTask() throws DukeException;

    /**
     * Creates a new Task based on command issued by the user, and adds it to the list of tasks.
     * @param tasks List of tasks.
     * @param ui UI to display to the user.
     * @throws DukeException Application-specific exception thrown during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        Task newTask = createTask();
        tasks.add(newTask);

        ui.showMessage(UiMessage.TASK_ADDED);
        ui.showMessage(UiMessage.HINT_LIST);
    }
}
