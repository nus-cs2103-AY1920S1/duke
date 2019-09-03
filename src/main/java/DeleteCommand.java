package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Ui;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;

public class DeleteCommand extends Command {
    public DeleteCommand() {
        this("");
    }

    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }
    
    public Command clone(String fullCommand) {
        return new DeleteCommand(fullCommand);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int itemId = Parser.parseDelete(this.fullCommand);
        try {
            Task item = tasks.remove(itemId);
            ui.printResponse("Noted. I've removed this task:  \n  "
                + item.toString() + "\n"
                + "Now you have " + tasks.size() +" tasks in the list.");
        } catch(IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! There is no item " + itemId + ".");
        }   
    }

    public boolean isExit() {
        return false;
    }
}