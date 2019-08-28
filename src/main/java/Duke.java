import java.io.*;
import java.util.Scanner;
import java.util.stream.Stream;
import java.lang.NullPointerException;

public class Duke {
    private final Storage storage;

    private TaskList tasks;

    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException d) {
                ui.showError();
            } catch (NullPointerException n) {
                ui.showError();
            }
        }

    }

    public static void main(String[] args) {
        new Duke("/Users/bj/java/Duke/data/duke.txt").run();
    }

}
