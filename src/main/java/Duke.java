import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke that takes in a file to add text into.
     *
     * @param filepath File that the task is added to.
     * @throws IOException If the named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public Duke(String filepath) throws IOException {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Contains the methods to start the bot and
     * start to take in inputs for the bot
     */
    public void run() {
        ui.greeting();
        ui.nextCommand();
    }

    /**
     * Main method.
     */
    public static void main(String[] args) throws IOException{
        new Duke("todo.txt").run();
    }

}
