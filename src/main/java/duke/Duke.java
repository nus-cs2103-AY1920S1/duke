package duke;

import duke.exception.DukeException;
import duke.command.Command;
import duke.parser.Parser;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a duke object.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit;

    private Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        isExit = false;
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Duke() {
        this(Storage.DEFAULT_FILEPATH);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Runs the program.
     */
    public void run() {
        ui.showWelcome();
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println();
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            isExit = c.isExit();
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
