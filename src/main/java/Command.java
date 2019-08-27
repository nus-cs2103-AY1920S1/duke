public abstract class Command {
    String fullCommand;
    String[] splitInput;

    /**
     * Contructor for general Command class.
     * @param fullCommand The line that the user types.
     */
    Command(String fullCommand) {
        this.fullCommand = fullCommand;
        splitInput = fullCommand.split(" ");
    }

    /**
     * Carries out implementation of Command.
     * @param tasks TaskList of all added tasks.
     * @param ui Ui to print output.
     * @param storage Storage to save task.
     * @throws DukeException When command cannot execute due to wrong user input.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
