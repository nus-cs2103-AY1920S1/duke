package duke.command;

import duke.util.exception.DukeException;
import duke.util.exception.ExceptionType;
import duke.task.TaskList;
import duke.util.Ui;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Represents a request from the user to mark a given task as done.
 */
public class DoneCommand implements Command {
    private Scanner s;

    /**
     * Constructs a new DoneCommand, given the full command issued by the user.
     * @param fullCommand Full command issued by the user.
     */
    public DoneCommand(String fullCommand) {
        super();
        this.s = new Scanner(fullCommand);
    }

    /**
     * Marks the task in the command issued by the user as done.
     * @param tasks List of tasks.
     * @param ui UI to display to the user.
     * @throws DukeException Application-specific exception thrown during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        try {
            // first, try to get taskId
            s.next(); // command (done), to be ignored
            int taskId = s.nextInt();

            // if ok, mark task as done
            tasks.getTask(taskId).markAsDone();
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            // user input after "done" is not an int, or is an invalid task ID
            throw new DukeException(ExceptionType.INVALID_ID);
        } catch (NoSuchElementException e) {
            // user input after "done" is blank
            throw new DukeException(ExceptionType.ID_BLANK);
        }
    }
}
