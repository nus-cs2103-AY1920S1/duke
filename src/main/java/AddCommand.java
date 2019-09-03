package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Ui;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;

public class AddCommand extends Command {
    public AddCommand() {
        this("");
    }

    public AddCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }
    
    public Command clone(String fullCommand) {
        return new AddCommand(fullCommand);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = Parser.parseTask(this.fullCommand);
        tasks.add(task);
        ui.printResponse("Got it. I've added this task:\n  "
                + task.toString() + "\n"
                + "Now you have " + tasks.size() +" tasks in the list.");
    }

    public boolean isExit() {
        return false;
    }
}