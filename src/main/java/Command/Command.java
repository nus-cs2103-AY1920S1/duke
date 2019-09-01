package duke.command;

import duke.core.Ui;
import duke.core.Storage;
import duke.helper.DukeException;
import duke.task.TaskList;

public abstract class Command {
    protected String inputCommand;
    protected boolean shouldExit;

    /**
     * Constructor for Command which will be inherited by all other commands.
     *
     * @param inputCommand String that is parsed from the Parser.
     * shouldExit is the boolean that will stop the for loop and exit the program.
     */
    public Command(String inputCommand) {
        this.inputCommand = inputCommand;
        this.shouldExit = false;
    }

    /**
     * Checks if the for loop should continue.public method called by Duke.run() in the for loop to
     * Inherited by all child classes as all Command classes need to check if should end for loop.
     *
     * @return shouldExit is the boolean that will stop the for loop and exit the program.
     */
    public boolean shouldExit() {
        return this.shouldExit;
    }

    /**
     * abstract method called by Duke.run() in the for loop.
     * Inherited by all child classes as it's the core of CommandPattern.
     *
     * @param tasks TaskList passed from the Duke main class, containing Array of Tasks.
     * @param ui Ui passed from the Duke main class, responsible for printing output to the user and obtaining input.
     * @param storage Storage passed from the Duke main class, responsible for updating duke.txt after every command.
     * @throws DukeException which will be dealed with by the Duke class.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

}
