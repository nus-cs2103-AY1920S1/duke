package duke;

public abstract class Command {
    String fullCommand;
    String[] splitInput;

    /**
     * Constructor for general Command class.
     * @param fullCommand The line that the user types.
     */
    Command(String fullCommand) {
        this.fullCommand = fullCommand;
        splitInput = fullCommand.split(" ");
    }

    /**
     * Carries out implementation of Command.
     * @param tasks TaskList of all added tasks.
     * @return Duke output string.
     * @throws DukeException When command cannot execute due to wrong user input.
     */
    public abstract String execute(TaskList tasks) throws DukeException;
}
