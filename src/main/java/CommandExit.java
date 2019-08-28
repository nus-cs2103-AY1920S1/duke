//package mypackage;

/**
 * Represents the exit command to exit the program.
 */
public class CommandExit extends Command {

    /**
     * Represents the full command.
     */
    private String string;

    /**
     * Constructor of the exit command.
     * @param fullCommand the parsed command
     */
    public CommandExit(String fullCommand) {
        string = fullCommand;
    }

    /**
     * Executes the exit command.
     * @param tasks the task list
     * @param ui the UI
     * @param storage the storage writer
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printString("Bye. Hope to see you again soon!");
        this.isExit = true;
    }
}
