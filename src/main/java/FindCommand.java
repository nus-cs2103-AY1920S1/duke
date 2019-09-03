package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Ui;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;

public class FindCommand extends Command {
    public FindCommand() {
        this("");
    }

    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }
    
    public Command clone(String fullCommand) {
        return new FindCommand(fullCommand);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList tempList = new TaskList();
        String phrase = Parser.parseFind(this.fullCommand);
        for(Task task : tasks) {
            if (task.toString().contains(phrase)) {
                tempList.add(task);
            }
        }
        ui.printResponse("Here are the matching tasks in your list:\n  "
                + tempList.toString() + "\n");
    }

    public boolean isExit() {
        return false;
    }
}