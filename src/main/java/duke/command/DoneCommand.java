package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class DoneCommand implements Command {
    private int taskId;

    public DoneCommand(int taskId) {
        super();
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.getTask(this.taskId).markAsDone(); // mark task as done
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            // user input after "done" is not an int, or is an invalid task ID
            ui.showError("Oops! You entered an invalid task ID!");
        } catch (NoSuchElementException e) {
            // user input after "done" is blank
            ui.showError("Oops! You did not enter a task ID!");
        }
    }
}
