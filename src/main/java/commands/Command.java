package commands;

import storage.Storage;
import util.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * Encapsulates a user command.
 *
 * <p> Abstract class whose execute method is to be implemented by
 * child classes. </p>
 *
 * @author atharvjoshi
 * @version CS2103 AY19/20 Sem 1 iP Week 4
 */
public abstract class Command {
    /** Constant to indicate the list indexing is one-based. */
    static final int ONE_BASED_LIST_INDEX = 1;

    /** The term used to identify command type. */
    private String imperative;

    /** Boolean depicting whether the command is used to exit Duke. */
    boolean isExit;

    /** Abstract execute method to be implemented by child classes. */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage)
            throws IOException;

    /**
     * Instantiate a command with an imperative.
     * @param imperative the term used to identify the command type.
     */
    Command(String imperative) {
        this.imperative = imperative;
        this.isExit = false;
    }

    /**
     * Returns whether command is used to exit ui.Duke.
     * @return true if exit command, false otherwise.
     */
    public boolean getIsExit() {
        return this.isExit;
    }

    /**
     * Parses a string into an integer corresponding to the index to the
     * task to be retrieved.
     *
     * @param indexString the string representing the task index.
     * @return the integer corresponding to indexString.
     */
    public int parseStringToIntIndex(String indexString) {
        return Integer.parseInt(indexString) - ONE_BASED_LIST_INDEX;
    }
}