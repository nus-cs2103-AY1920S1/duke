package jermi.command;

import jermi.component.Storage;
import jermi.component.TaskList;
import jermi.component.Ui;
import jermi.exception.JermiException;
import jermi.exception.NotANumberException;
import jermi.task.Task;

public class DeleteCommand extends Command {
    private int ordering;

    public DeleteCommand(String ordering) throws JermiException {
        super();

        try {
            this.ordering = Integer.parseInt(ordering);
        } catch (NumberFormatException e) {
            throw new NotANumberException();
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JermiException {
        Task task = taskList.getTask(ordering);
        taskList.remove(ordering);
        int numOfTasks = taskList.getSize();
        ui.echo("Noted. I've removed this task:"
                , "  " + task
                , String.format("Now you have %d task%s in the list.", numOfTasks, numOfTasks == 1 ? "" : "s"));
        storage.taskListToFile();
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
