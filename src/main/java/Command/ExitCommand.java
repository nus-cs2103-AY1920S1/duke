package duke.command;

import duke.core.Ui;
import duke.core.Storage;
import duke.helper.DukeException;
import duke.task.TaskList;

public class ExitCommand extends Command {

    /**
     * Constructor for ExitCommand which inherits from Command.
     *
     * @param inputCommand String that is parsed from the Parser.
     */
    public ExitCommand(String inputCommand) {
        super(inputCommand);
    }

    /**
     * Assigns the boolean shouldExit as true from parent class Command, thus terminating the for loop and closing Duke.
     * public method called by Duke.run() in the for loop as part of the Command Pattern.
     * Needs to be implemented as inherited from Command abstract method.
     *
     * @param tasks TaskList passed from the Duke main class, containing Array of Tasks.
     * @param ui Ui passed from the Duke main class, responsible for printing output to the user and obtaining input.
     * @param storage Storage passed from the Duke main class, responsible for updating duke.txt after every command.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        this.shouldExit = true;
        ui.printBye();
    }
}
