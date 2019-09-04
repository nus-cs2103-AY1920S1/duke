package duke.command;

import duke.error.DukeException;
import duke.task.Task;
import duke.Parser;
import duke.Ui;
import duke.Storage;
import duke.TaskList;

/**
 * subclass of command.
 * operation to update tasks: done commands
 * */
public class UpdateCommand extends Command {
    protected String command;
    protected int pos;
    protected Parser parser = new Parser();

    public UpdateCommand(String command) {
        this.command = command;
    }

    /**
     * main method to perform operation.
     * update task and text file (in storage)
     * if successful, print out updated task
     * */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (parser.validNumber(command, tasks.getList().size())) {
                pos = Integer.parseInt(command);
                Task currTask = tasks.updateTask(pos);
                storage.updateTasks(currTask, "done", pos - 1);
                return ui.printDoneTask(currTask);
            }
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
        return "";

    }

    public boolean isExit() {
        return false;
    }
}