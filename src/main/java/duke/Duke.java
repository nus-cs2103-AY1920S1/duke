package duke;

import duke.command.Command;

import duke.storage.Storage;

import duke.task.TaskList;

import duke.ui.Ui;

import duke.exception.DukeException;

import duke.parser.Parser;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor. Creates Ui, Storage and TaskList object.
     * If the file does not exist, an error will be shown.
     *
     * @param filePath Directory to the data file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath, ui);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Returns the welcome message and gets Ui to show the current tasks in the list.
     */
    public String getWelcomeMessage() {
        return ui.showWelcome(taskList.getTasks());
    }

    /**
     * You should have your own function to generate a response to user input.
     * @param input User's input.
     */
    protected String getResponse(String input) {
        String response = "";
        try {
            Command c = Parser.parse(input);
            response = c.execute(taskList, ui, storage);
            storage.updateData(taskList.getTasks());
        } catch (DukeException e) {
            response = e.getMessage();
        } finally {
            return response;
        }
    }
}
