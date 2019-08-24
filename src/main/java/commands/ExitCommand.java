package commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * ExitCommand is a class that terminates the program.
 */
public class ExitCommand extends Command {

    /**
     * Constructor for ExitCommand.
     * Boolean isExit is set to true because
     * program should be terminated after command is executed.
     *
     * @param fullCommand the line of user input.
     */
    public ExitCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        isExit = true;
    }

    /**
     * Exits the program.
     *
     * @param tasks the TaskList object storing all recorded Tasks.
     * @param ui the Ui object dealing with user interaction.
     * @param storage the Storage object that reads from and writes to the file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("     Bye. Hope to see you again soon!");
    }

}
