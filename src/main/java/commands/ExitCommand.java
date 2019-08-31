package commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

import java.io.IOException;

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
     * @return String output reply from Duke.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // Save the new task list to the hard disk
        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showSavingError(e.getMessage());
        }
        return String.format("     Bye. Hope to see you again soon!\n");
    }

}
