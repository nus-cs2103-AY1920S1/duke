package duke.cli;

import duke.command.Command;
import duke.command.CommandResult;
import duke.exception.DukeException;
import duke.exception.DukeStorageException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Cli;

public final class DukeCli {
    private final Storage storage;
    private final TaskList tasks;
    private final Cli ui;

    /**
     * The Duke CLI application.
     */
    public DukeCli() {
        storage = new Storage("duke.txt");
        tasks = new TaskList();
        ui = new Cli(System.in, System.out);
    }

    /**
     * Run the Duke CLI application.
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
                CommandResult result = command.execute(tasks, storage);
                for (String message : result.getMessages()) {
                    ui.showMessage(message);
                }
                for (String warning : result.getWarnings()) {
                    ui.showWarning(warning);
                }
                if (result.isExit()) {
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
