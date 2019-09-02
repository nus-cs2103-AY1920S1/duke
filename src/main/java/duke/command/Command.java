package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;
import duke.exception.DukeException;

/**
 * Represents user commmands to chatbot.
 * The 'Command' class supports operators (i) executing the command
 * and (ii) checking if the bot has exited its conversation with the user.
 */
public abstract class Command {

    /**
     * Executes the command and print out respecive reponse.
     *
     * @param taskList List of the things user needs to do
     * @param ui       Interface that interacts with the user
     * @param storage  Stores the user input in a file
     * @throws DukeException IOException if there is an error writing or reading file
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns a boolean value on whether the program has exited.
     *
     * @return boolean of whether program has exited
     */
    public abstract boolean isExit();
}