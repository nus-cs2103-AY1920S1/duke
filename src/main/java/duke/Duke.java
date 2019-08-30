package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    private Duke() {
        storage = new Storage("duke.txt");
        tasks = new TaskList();
        ui = new Ui(System.in, System.out);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    private void run() {
        ui.showWelcome();

        try {
            tasks = storage.loadTasks();
        } catch (DukeException e) {
            ui.showWarning("Failed to load tasks from disk. " + e.getMessage());
        }

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                if (input == null) {
                    input = "bye";
                }
                ui.showSeparator();
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showSeparator();
            }
        }
    }
}
