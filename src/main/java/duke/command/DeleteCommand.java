package duke.command;

import duke.util.exception.DukeException;
import duke.util.exception.ExceptionType;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Ui;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DeleteCommand implements Command {
    private Scanner s;

    public DeleteCommand(String fullCommand) {
        super();
        this.s = new Scanner(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        try {
            // first, try to get taskId
            s.next(); // command (delete), to be ignored
            int taskId = s.nextInt();

            // if ok, delete task
            Task task = tasks.getTask(taskId);
            tasks.deleteTask(task);
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            // user input after "done" is not an int, or is an invalid task ID
            throw new DukeException(ExceptionType.INVALID_ID);
        } catch (NoSuchElementException e) {
            // user input after "done" is blank
            throw new DukeException(ExceptionType.ID_BLANK);
        }
    }
}
