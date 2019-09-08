package duke.command;

import duke.component.DukeDatabase;
import duke.component.TaskList;
import duke.exception.DukeException;

/**
 * Encapsulates a command to be executed by duke.Duke bot.
 */
public abstract class Command {
    /**
     * Executes the command that this object encapsulates.
     *
     * @param t the taskList of duke.Duke.
     * @param d the database of duke.Duke.
     * @throws DukeException depending on the actual Command type of the object.
     */
    public abstract String execute(DukeDatabase d, TaskList t) throws DukeException;

    /**
     * Returns the phrase "N word" or "N words" (singular or plural).
     * N is the the number of tasks in the taskList.
     */
    protected String getTaskPhrase(int size) {
        return size > 1 ? size + " tasks" : size + " task";
    }
}
