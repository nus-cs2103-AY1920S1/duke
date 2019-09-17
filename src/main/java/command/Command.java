package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.VocabularyList;

/**
 * Abstract command which has many different type of commands.
 *
 */
public abstract class Command {

    /**
     * Executes the given line instruction passed into the object.
     *
     * @param taskList Tasklist object containing the data structure.
     * @param ui duke Ui object responsible for interface response.
     * @param storage duke Storage object to make changes to hard drive data.
     * @param vocabularyList VocabularyList object that is needed by TriviaCommand.
     * @return Response string from the Bot to the user.
     * @throws DukeException Any error encountered by executing the command.
     */
    public abstract String getResponse(TaskList taskList, Ui ui,
                                       Storage storage, VocabularyList vocabularyList) throws DukeException;

    /**
     * Indicates if the Command signals for program termination.
     *
     * @return A boolean if the program should terminate.
     */
    public boolean isExit() {
        return false;
    }

}
