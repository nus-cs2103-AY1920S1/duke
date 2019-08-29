/**
 * Acts as the main interface
 */

import java.io.FileNotFoundException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates an instance of Duke with a path of the task list file to run.
     * Instance of Storage loads data from the file.
     * Exception is reported if file not found.
     *
     * @param filePath Stores file of the path in which list of tasks are stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Creates new instance of Parser and runs parse method of Parser for user input.
     * Default greeting message is printed using Ui object.
     */
    public void run() {
        ui.greet();
        Parser parser = new Parser(storage, tasks, ui);
        parser.parse();
    }

    /**
     * Creates a new instance of Duke with a path to task list file and runs.
     * @param args
     */
    public static void main(String[] args) {
        new Duke("../data/duke.txt").run();
    }
}
