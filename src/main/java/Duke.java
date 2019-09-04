import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


/**
 * Represents the task manager.
 */
public class Duke {

    /**
     * The storage that loads and save tasks.
     */
    private Storage storage;

    /**
     * The task list that edits the task manager.
     */
    private TaskList tasks;

    /**
     * The user interface that communicates with the user.
     */
    private Ui ui;

    /**
     * Constructs the file manager.
     * @param filePath path of the saved file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program.
     */
    public void run() {
        boolean isExit = false;
        ui.showWelcome();
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit;
            } catch (DukeException e) {
                ui.showError(e.description);
            } finally {
                ui.showLine();
            }
        }

    }

    /**
     * Main method of the program.
     * @param args inputs
     * @throws Exception error exceptions
     */
    public static void main(String[] args) throws Exception{
        new Duke("data/duke.txt").run();
    }

}
