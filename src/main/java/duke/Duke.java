package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parse.Parser;
import duke.task.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    private boolean isExit;

    /**
     * Replace this stub with completed method.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            isExit = command.isExit();
            return command.execute(tasks, ui, storage);
        } catch (DukeException ex) {
            return ui.showError(ex.getMessage());
        }
    }

    /**
     * Constructor for Duke object.
     */
    public Duke() {
        storage = new Storage("//Users//chowjiaying//Github//2103T-iP//duke//data//duke.txt");
        tasks = new TaskList(storage.load());
        ui = new Ui();
    }

    /**
     * Getter method for isExit.
     * @return boolean value of isExit
     */
    public boolean isExit() {
        return isExit;
    }
}