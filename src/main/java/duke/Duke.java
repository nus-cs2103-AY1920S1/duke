package duke;

import duke.command.Command;
import duke.task.TaskList;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

public abstract class Duke {
    protected static final String DEFAULT_FILE_PATH = "tasks.txt";
    private Parser parser;
    protected Ui ui;

    /**
     * Constructs a new copy of the Duke application.
     *
     * @param filePath File path of the save file to store tasks.
     */
    protected Duke(String filePath, Ui ui) {
        this.ui = ui;
        ui.showWelcome();
        Storage storage = new Storage(filePath);
        TaskList tasks;
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            if (!(e instanceof NoSuchFileException)) {
                if (e instanceof DukeException) {
                    ui.showError(e.getMessage());
                } else {
                    e.printStackTrace();
                }
                ui.showError("Could not load tasks. Proceeding may overwrite old tasks.");
            }
            tasks = new TaskList();
        }
        parser = Parser.getForDefaultCommands(tasks, storage);
    }

    protected boolean runInput(String input) {
        String[] words = input.split(" ");
        try {
            Command command = parser.parse(words);
            assert command != null;
            ui.showMessage(command.run(words));
            return command.isExit();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } catch (IOException e) {
            ui.showError(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
