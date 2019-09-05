package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UserInterface;

/**
 * Represents the logic of application.
 *
 * @author Ng Jun Hao
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private UserInterface ui;
    private static final String DEFAULT_STORAGE_FILEPATH = "data/tasks.txt";

    /**
     * Constructor for instantiating a Duke session.
     */
    public Duke() {
        ui = new UserInterface();
        storage = new Storage(DEFAULT_STORAGE_FILEPATH);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showExceptionMessage(e.getMessage());
            taskList = new TaskList();
            ui.exitProgram();
        }
    }

    /**
     * Generates a response to user input.
     *
     * @param input user input.
     * @return response.
     */
    String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(taskList, storage);
        } catch (DukeException e) {
            return "OOPS!!! " + e.getMessage();
        }
    }
}
