/**
 * The Duke class
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Duke object.
     *
     * @param filePath A string representing the directory of the file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Loads and saves files by applying other classes.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        }
    }

    /**
     * This main program implements an application of the classes that will print all the outputs
     * and it loads and saves files.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}




