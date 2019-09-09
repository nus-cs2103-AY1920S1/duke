/**
 * The main class for managing all the java files.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Instantiate a Duke object when a directory parameter is passed
     * into it. Will also instantiate the Ui, Storage and TaskList objects.
     *
     * @param filePath the directory for the designated path to store the tasks.
     */
    protected Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Initialise the Parser class to interpret User's command.
     */
    protected void setParser() {
        parser = new Parser(tasks, ui);
    }

    /**
     * Project the welcome message when User start the application.
     * @return Welcome message.
     */
    protected String getWelcomeMessage() {
        return ui.showWelcome();
    }

    /**
     * Duke response to User after User's input.
     * @param input User's input.
     * @return The response of Duke.
     */
    protected String getResponse(String input) {
        return parser.parse(input);
    }

    /**
     * Save the task list into the designated .txt file
     */
    protected void saveList() {
        storage.save(parser.retrieveTasks());
    }
}