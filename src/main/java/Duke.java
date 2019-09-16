import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke.
     *
     * @throws IOException If the named file exists but is a directory rather than a regular file,
     *                     does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public Duke() throws IOException {
        ui = new Ui();
        storage = new Storage(Storage.file);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Constructor for Duke that takes in a file to add text into.
     *
     * @param filepath File that the task is added to.
     * @throws IOException If the named file exists but is a directory rather than a regular file,
     *         does not exist but cannot be created, or cannot be opened for any other reason.
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
     * start to take in inputs for the bot.
     */
    public void run() {
        System.out.println("test");
        ui.greeting();
        boolean isThereANextCommand = false;
        while (!isThereANextCommand) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                System.out.println(c.execute(tasks, ui, storage));
                isThereANextCommand = c.shouldExit();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    /**
     * Main method.
     */
    public static void main(String[] args) throws IOException {
        new Duke("todo.txt").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) throws DukeException, IOException {
        try {
            String fullCommand = input;
            Command c = Parser.parse(fullCommand);
            return (c.execute(tasks, ui, storage));
        } catch (DukeException | IOException e) {
            ui.showError(e.getMessage());
            return e.getMessage();
        }
    }
}