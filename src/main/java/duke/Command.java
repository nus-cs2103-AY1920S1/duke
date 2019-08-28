package duke;

public abstract class Command {
    String fullCommand;
    String[] splitInput;

    /**
     * Contructor for general Duke.Duke.Command class.
     * @param fullCommand The line that the user types.
     */
    Command(String fullCommand) {
        this.fullCommand = fullCommand;
        splitInput = fullCommand.split(" ");
    }

    /**
     * Carries out implementation of Duke.Duke.Command.
     * @param tasks Duke.Duke.TaskList of all added tasks.
     * @param ui Duke.Duke.Ui to print output.
     * @throws DukeException When command cannot execute due to wrong user input.
     */
    public abstract void execute(TaskList tasks, Ui ui) throws DukeException;
}
