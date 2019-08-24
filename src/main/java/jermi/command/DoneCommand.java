package jermi.command;

import jermi.component.Storage;
import jermi.component.TaskList;
import jermi.component.Ui;
import jermi.exception.JermiException;
import jermi.exception.NotANumberException;
import jermi.task.Task;

public class DoneCommand extends Command {
    private int ordering;

    public DoneCommand(String ordering) throws JermiException {
        super();

        try {
            this.ordering = Integer.parseInt(ordering);
        } catch (NumberFormatException e) {
            throw new NotANumberException();
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JermiException {
        Task task = taskList.getTask(this.ordering);
        task.markAsDone();
        ui.echo("Nice! I've marked this task as done:", "  " + task);
        storage.taskListToFile();
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
