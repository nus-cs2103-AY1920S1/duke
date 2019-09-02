/** Class to represent Duke. */
public class Duke {

    // class variables
    private UI ui;
    private Parser parser;
    private Storage storage;
    private TaskList tl;
    private CommandHandler commandHandler;

    /**
     * Class constructor for the Duke class.
     */
    public Duke(String filePath) {
        this.ui = new UI();
        this.parser = new Parser();
        this.storage = new Storage(filePath);
        try {
            this.tl = new TaskList(storage.readTasksFromFile());
            this.commandHandler = new CommandHandler(this.ui, this.parser, this.storage, this.tl);
        } catch (DukeException e) {
            this.ui.printError(e);
            System.exit(0);
        }
    }

    /**
     * Method to generate the welcome string for Duke.
     * @return Welcome string to be returned.
     */
    public String getWelcomeString() {
        return this.ui.printWelcome() + "\n" + this.ui.showTaskList(this.tl);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            return this.commandHandler.execute(input);
        } catch (DukeException e) {
            return this.ui.printError(e);
        }
    }
}
