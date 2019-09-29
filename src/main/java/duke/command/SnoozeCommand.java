package duke.command;

import duke.task.TaskList;
import duke.util.Ui;
import duke.util.exception.DukeException;
import duke.util.exception.ExceptionType;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Represents a command to postpone ("snooze") a task.
 */
public class SnoozeCommand implements Command {
    private Scanner s;

    /**
     * Constructs a new SnoozeCommand, given the full command issued by the user.
     * @param fullCommand Full command issued by the user.
     */
    public SnoozeCommand(String fullCommand) {
        super();
        this.s = new Scanner(fullCommand);
    }
    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        try {
            // first, try to get taskId
            s.next(); // command (done), to be ignored
            int taskId = s.nextInt();

            // if ok, snooze task
            tasks.getTask(taskId).snooze();
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            // user input after "done" is not an int, or is an invalid task ID
            throw new DukeException(ExceptionType.INVALID_ID);
        } catch (NoSuchElementException e) {
            // user input after "done" is blank
            throw new DukeException(ExceptionType.ID_BLANK);
        }
    }
}
