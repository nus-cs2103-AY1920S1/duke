package duke;

import java.io.File;
import java.io.IOException;
import exceptions.DukeException;
import commands.Command;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showOpeningLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showClosingLine();
            }
        }
        // Save the new task list to the hard disk
        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showSavingError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        String filePath = "." + File.separator + "data" + File.separator + "duke.txt";
        new Duke(filePath).run();
    }

}
