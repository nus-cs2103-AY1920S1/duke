package dose.command;

import dose.task.Task;
import dose.task.TaskList;
import dose.util.exception.DoseException;
import dose.util.exception.ExceptionType;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public abstract class ModifyTaskCommand implements Command{
    Scanner s;

    public ModifyTaskCommand(String fullCommand) {
        super();
        s = new Scanner(fullCommand);
    }

    public Task getTaskById(TaskList tasks) throws DoseException {
        int taskId;
        Task task;

        try {
            // first, try to get taskId
            s.next(); // command, to be ignored
            taskId = s.nextInt();
            task = tasks.getTask(taskId);

            return task;
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            // user input after "tag" is not an int, or is an invalid task ID
            throw new DoseException(ExceptionType.INVALID_ID);
        } catch (NoSuchElementException e) {
            // user input after "tag" is blank
            throw new DoseException(ExceptionType.ID_BLANK);
        }
    }
}
