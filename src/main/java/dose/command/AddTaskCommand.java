package dose.command;

import dose.task.Task;
import dose.task.TaskList;
import dose.util.Storage;
import dose.util.Ui;
import dose.util.UiMessage;
import dose.util.exception.DoseException;
import dose.util.exception.ExceptionType;
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
     * @throws DoseException Application-specific exception thrown during execution.
     */
    void setDescription() throws DoseException {
        try {
            this.description = this.s.next().strip();
        } catch (NoSuchElementException e) {
            // user input after task type is blank
            throw new DoseException(ExceptionType.DESCRIPTION_BLANK);
        }
    }

    /**
     * Retrieves the string representing the deadline of the task to be added,
     * based on command issued by user.
     * @throws DoseException Application-specific exception thrown during execution.
     */
    void setDeadlineString() throws DoseException {
        try {
            this.deadlineString = this.s.next().strip();
        } catch (NoSuchElementException e) {
            // no deadline entered
            throw new DoseException(ExceptionType.DEADLINE_BLANK);
        }
    }

    /**
     * Creates the task to be added, based on command issued by the user.
     * @return Task to be added.
     */
    abstract Task createTask() throws DoseException;

    /**
     * Creates a new Task based on command issued by the user, and adds it to the list of tasks.
     * @param tasks List of tasks.
     * @param ui UI to display to the user.
     * @param storage Object that handles storage of task list to disk.
     * @throws DoseException Application-specific exception thrown during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DoseException {
        Task newTask = createTask();
        tasks.add(newTask);

        String message = UiMessage.TASK_ADDED.getMessage() + " " + UiMessage.HINT_LIST.getMessage();
        ui.showMessage(message);
    }
}
