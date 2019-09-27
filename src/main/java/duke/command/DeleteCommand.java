package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class DeleteCommand implements Command {
    private int taskId;
    
    public DeleteCommand(int taskId) {
        super();
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.getTask(taskId);
            tasks.deleteTask(task); // mark task as done
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            // user input after "done" is not an int, or is an invalid task ID
            ui.showError("Oops! You entered an invalid task ID!");
        } catch (NoSuchElementException e) {
            // user input after "done" is blank
            ui.showError("Oops! You did not enter a task ID!");
        }
    }
}
