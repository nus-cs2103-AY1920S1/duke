package duke;

import static duke.ui.Ui.MESSAGE_RETURN;
import static duke.ui.Ui.MESSAGE_WELCOME;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    private static final String DEFAULT_FILE_PATH = "tasks.txt";

    /**
     * Returns output in response to input command.
     * @param input User command.
     * @return String representation of the output from the input command.
     */
    public String getResponse(String input) {
        ui.resetMessage();
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            storage.save(tasks);
        } catch (DukeException ex) {
            ui.append(ex.getMessage());
        }
        return ui.getMessage();
    }

    /**
     * Starts Duke using the default file path.
     */
    public Duke() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Starts Duke.
     *
     * @param filePath File path of the tasks data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
            ui.append(String.format("Loaded from %s", storage.getFilePath()));
            ui.append("----------------------");
        } catch (DukeException ex) {
            tasks = new TaskList();
        }
    }

    /**
     * Returns greeting message.
     * @return Greeting message.
     */
    public String getGreeting() {
        if (tasks.isEmpty()) {
            ui.append(MESSAGE_WELCOME);
        } else {
            ui.append(MESSAGE_RETURN);
        }
        return ui.getMessage();
    }
}

