package duke.command;

import duke.task.TaskList;
import duke.util.Ui;
import duke.util.exception.DukeException;
import duke.util.exception.ExceptionType;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DeleteCommand implements Command {
    private Scanner s;

    /**
     * Constructs a new DeleteCommand, given the full command issued by the user.
     * @param fullCommand Full command issued by the user.
     */
    public DeleteCommand(String fullCommand) {
        super();
        this.s = new Scanner(fullCommand);
    }

    /**
     * Deletes a task, based on command issued by the user.
     * @param tasks List of tasks.
     * @param ui UI to display to the user.
     * @throws DukeException Application-specific exception thrown during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        try {
            // first, try to get taskId
            s.next(); // command (delete), to be ignored
            int taskId = s.nextInt();

            // if ok, delete task
            tasks.deleteTask(taskId);
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            // user input after "done" is not an int, or is an invalid task ID
            throw new DukeException(ExceptionType.INVALID_ID);
        } catch (NoSuchElementException e) {
            // user input after "done" is blank
            throw new DukeException(ExceptionType.ID_BLANK);
        }
    }
}
