package duke;

import duke.command.Command;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;
import duke.util.UiMessage;
import duke.util.cli.Cli;
import duke.util.exception.DukeException;

public class Duke {
    Storage storage;
    TaskList tasks;
    String filePath = "data/tasks.txt";

    /** CLI implementation of Duke uses a Cli object to represent its UI. */
    Ui ui;

    /**
     * Creates a new instance of Duke, with the default filePath.
     */
    public Duke() {
        ui = new Cli();
        storage = new Storage(filePath);
        tasks = new TaskList();
    }

    /**
     * Runs Duke from the CLI. All output is displayed in the CLI.
//     * Not used when Duke is run from the GUI.
     */
    void run() {
        ui.showMessage(UiMessage.WELCOME);
        initializeStorage();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
        storage.save(tasks);
    }

    /**
     * Creates and runs a new instance of Duke.
     * Invoked when Duke is run from the CLI.
     * @param args Arguments supplied by the user.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Attempts to import an existing task list.
     */
    private void initializeStorage() {
        try {
            TaskList tasksFromFile = storage.load();
            tasks = tasksFromFile;
        } catch (Exception e) {
            // temporary haxx
            e.printStackTrace();
        }
    }
}
