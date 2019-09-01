package duke.command;

import duke.core.Ui;
import duke.core.Storage;
import duke.helper.DukeException;
import duke.task.TaskList;
import duke.task.Task;
import java.util.ArrayList;

public class FindCommand extends Command {

    /**
     * Constructor for FindCommand which inherits from Command.
     *
     * @param inputCommand String that is parsed from the Parser.
     */
    public FindCommand(String inputCommand) {
        super(inputCommand);
    }

    /**
     * Reads and creates new ToDo, Event or Deadline based on the parsed input.
     * public method called by Duke.run() in the for loop as part of the Command Pattern.
     * Needs to be implemented as inherited from Command abstract method.
     * Uses Ui class to print responses and TaskList to obtain the shortlisted and found arraylist.
     *
     * @param tasks TaskList passed from the Duke main class, containing Array of Tasks.
     * @param ui Ui passed from the Duke main class, responsible for printing output to the user and obtaining input.
     * @param storage Storage passed from the Duke main class, responsible for updating duke.txt after every command.
     * @throws DukeException which can come from one sources, itself if the description of the find is empty or has more
     * than one keyword
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] inputsplit = this.inputCommand.split(" ");
        if (inputsplit.length <= 1) {
            throw new DukeException("OOPS!!! The description of a find Command cannot be empty.");
        } else if (inputsplit.length > 2) {
            throw new DukeException("OOPS!!! The find Command can only accept one keyword.");
        } else {
            ArrayList<Task> taskToFind = tasks.findTask(inputsplit[1]);
            ui.printFind(taskToFind);
        }
    }

}
