package duke.cli;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeStorageException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * The Duke CLI application.
 */
public final class DukeCli {
    private final Storage storage;
    private final TaskList tasks;
    private final Cli ui;

    /**
     * Constructs a Duke CLI application.
     */
    public DukeCli() {
        storage = new Storage("duke.txt");
        tasks = new TaskList();
        ui = new Cli(System.in, System.out);
    }

    /**
     * Runs the Duke CLI application.
     */
    public void run() {
        ui.showWelcome();

        try {
            storage.loadTasks(tasks);
        } catch (DukeStorageException e) {
            ui.showWarning(e.getMessage());
        }

        while (true) {
            try {
                String input = ui.readCommand();
                if (input == null) {
                    input = "bye";
                }
                ui.showSeparator();
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                if (command.shouldExit()) {
                    break;
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showSeparator();
                ui.showMessage("");
            }
        }
    }
}
