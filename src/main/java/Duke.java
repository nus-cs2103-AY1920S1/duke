import java.io.IOException;

/**
 * A program that keeps track of a person's tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList listOfTasks;
    private Ui ui;

    public static final String EXIT_PROGRAM = "Exit program";

    /**
     * Constructor for Duke.
     *
     * @param filePath a String representing the file path
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        listOfTasks = new TaskList(storage.load());
    }

    /**
     * Method to run the program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(listOfTasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main method.
     */
    public static void main(String[] args) throws IOException {
        new Duke("duke.txt").run();
    }

    /**
     * Parses and execute the input given, and returns a response.
     *
     * @param input a String representing the input from user
     * @return a String representing the response
     */
    public String getResponse(String input) {
        if (input == null) {
            return null;
        }
        try {
            Command c = Parser.parse(input);
            c.execute(listOfTasks, ui, storage);
            if (c.isExit()) {
                return EXIT_PROGRAM;
            } else {
                String response = ui.getMessage();
                assert response != null;
                return response;
            }
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }
}