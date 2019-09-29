package duke.command;

import duke.util.exception.DukeException;
import duke.util.exception.ExceptionType;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DoneCommand implements Command {
    private Scanner s;

    public DoneCommand(String fullCommand) {
        super();
        this.s = new Scanner(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
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
