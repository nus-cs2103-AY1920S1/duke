
import java.io.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
        }
    }

    public void run() {
        ui.showWelcome();
        String fullCommand = ui.readCommand();
        while (!fullCommand.equals("bye")) {
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
            fullCommand = ui.readCommand();
        }

        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showError(e.getMessage());
        } finally {
            ui.exit();
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}
