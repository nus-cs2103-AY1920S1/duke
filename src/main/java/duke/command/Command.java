package duke.command;

import duke.task.Task;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a valid command for Duke.
 */
public abstract class Command {
    /** Inputted arguments behind the command keyword. */
    String args;
    /** Task associated with the command's execution. */
    protected Task task;

    /**
     * Constructor for commands with no arguments in the input.
     */
    Command() {
    }

    /**
     * Constructor for commands with arguments in the input.
     *
     * @param args Inputted arguments behind the command keyword.
     */
    Command(String args) {
        this.args = args;
    }

    /**
     * Constructor for commands with a Task associated associated to its execution.
     *
     * @param task Task associated with the command's execution.
     */
    Command(Task task) {
        this.task = task;
    }

    /**
     * Execute the actions associated with command.
     * This includes the managing of Tasks in the TaskList, the input, output and storage.
     *
     * @param tasks List of Tasks Duke is currently tracking.
     * @param ui User Interface of Duke that handles input and output to and from the command line.
     * @param storage Storage where the Tasks are retrieved from and stored to.
     * @throws DukeException If inputted arguments are invalid.
     */
    public abstract void executeCli(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract String executeGui(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns true if the command is "bye", else returns false.
     *
     * @return True if command is "bye", false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
