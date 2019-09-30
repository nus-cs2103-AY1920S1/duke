package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.commons.Messages;
import seedu.duke.exception.DukeException;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.UI;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    /**
     * Constructs a Duke object. Reads and loads the latest record of the Task List
     * from the file that stores the list.
     * @param filePath Represents the file address of the file to be read.
     */
    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Takes in command from user and executes the command. Returns a reply as String.
     */
    protected String getResponse(String input) {
        try {
            Command c = new Parser().parse(input);
            String response =  c.execute(tasks, ui, storage);
            return response;
        } catch (DukeException e) {
            return Messages.MESSAGE_UNKNOWN_COMMAND;
        } catch (ArrayIndexOutOfBoundsException e) {
            return Messages.MESSAGE_EMPTY_TASK_DESCRIPTION;
        }
    }
}