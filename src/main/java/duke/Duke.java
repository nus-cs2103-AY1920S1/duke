package duke;

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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Starts Duke using the default file path.
     */
    public Duke() {
        new Duke(DEFAULT_FILE_PATH);
    }

    /**
     * Starts Duke.
     * @param filePath File path of the tasks data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
            Ui.printIndented(String.format("Loaded from %s", storage.getFilePath()));
        } catch (DukeException ex) {
            Ui.printIndented(ex.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs Duke.
     */
    private void run() {
        Ui.sayGreeting();
        boolean isExit = false;

        while (!isExit) {
            String fullCommand = ui.nextCommand();
            Ui.printDivider();
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, storage);
                storage.save(tasks);
                isExit = c.isExit();
            } catch (DukeException ex) {
                Ui.printIndented(ex.getMessage());
            } finally {
                Ui.printDivider();
            }
        }
    }
}
