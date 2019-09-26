package commands;

import exceptions.DukeException;
import utils.Storage;
import utils.TaskList;
import utils.Ui;

import java.text.ParseException;

public abstract class Command {
    protected boolean isExit;

    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Execute different behaviors based on the nature of the command.
     *
     * @param tasks to access the list of tasks
     * @param ui to give feedback to user
     * @param storage to write changes to file
     * @return feedback from duke
     * @throws DukeException in case of errors writing to file
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

}
