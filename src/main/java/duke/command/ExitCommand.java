package duke.command;

import java.io.IOException;
import duke.exception.DukeException;
import duke.io.Ui;
import duke.io.Storage;

import duke.task.TaskList;

public class ExitCommand extends Command {
    public ExitCommand(String command) {
        super(command);
    }

    /**
     * Quits the program and returns a String containing the good-bye message.
     *
     * @param t the TaskList object
     * @param ui the Ui object
     * @param storage the Storage object
     * @return a String representing good-bye message
     */
    public String execute(TaskList t, Ui ui, Storage storage) throws DukeException {
        assert this.command.equals("bye") : "User should have input \"bye\"";
        try {
            storage.writeToFile(t.asFileFormattedList());
            return ui.asDukeMessage(
                new String[]{"Thank you for using duke.ui.Duke!", "These are your tasks:"},
                new String[]{"duke.ui.Duke is exiting..."},
                t.asFormattedList().toArray(new String[t.size()])
            );
        } catch (IOException e) {
            throw new DukeException("Saving to file failed.");
        }
    }
}
