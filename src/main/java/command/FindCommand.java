package duke.command;

import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Ui;
import duke.util.Storage;
import duke.task.TaskList;
import duke.task.Task;

/**
 * Command containing method for finding Tasks in TaskList.
 */
public class FindCommand extends Command {
    /**
     * Constructor for FindCommand without parameters.
     */
    public FindCommand() {
        this("");
    }

    /**
     * Constructor for DeleteCommand with String parameter.
     * 
     * @param fullCommand Input entered by user.
     */
    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }
    
    /**
     * Returns a FindCommand as initialized by the constructor.
     * 
     * @param fullCommand Input entered by user.
     */
    public Command clone(String fullCommand) {
        return new FindCommand(fullCommand);
    }

    /**
     * Finds Tasks from the TaskList that matches the query.
     *
     * @param tasks TaskList to find Tasks from.
     * @param ui Ui for printing responses to the console.
     * @param storage Storage that stores the modified TaskList.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList tempList = new TaskList();
        String query = Parser.parseFind(this.fullCommand);
        for (Task task : tasks) {
            if (task.toString().contains(query)) {
                tempList.add(task);
            }
        }
        ui.printResponse("Here are the matching tasks in your list:\n  "
                + tempList.toString() + "\n");
    }

    /**
     * Returns boolean to initiate exit of program.
     * 
     * @return false so program does not exit.
     */
    public boolean isExit() {
        return false;
    }
}