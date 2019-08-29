import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.getData());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    private void run() {
        ui.showWelcome();
        ui.straightLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.straightLine(); // show the divider line ("_______")
                Command c = DukeParser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.toString());
            } finally {
                ui.straightLine();
            }
        }
    }

    public static void main(String[] args) {
        String filePath= "data/dukeData.txt";
        new Duke(filePath).run();
    }
}
