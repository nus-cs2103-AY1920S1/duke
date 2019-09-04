package duke.command;

import duke.error.DukeException;
import duke.task.Task;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * subclass of command.
 * execute delete tasks operations
 * */
public class DeleteCommand extends Command {
    protected String command;
    protected int pos;
    protected Parser parser = new Parser();

    public DeleteCommand(String command) {
        this.command = command;
    }

    /**
     * main method to perform operation.
     * delete task in TaskList and update text file (in storage)
     * if successful, print out deleted task to user
     * */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (parser.validNumber(command, tasks.getList().size())) {
                pos = Integer.parseInt(command);
                Task currTask = tasks.deleteTask(pos);
                storage.updateTasks(currTask, "delete", pos - 1);
                return ui.printDeletedTask(currTask, tasks.getList().size());
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