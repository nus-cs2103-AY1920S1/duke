/**
 * Handles the details of the Duke System,
 * receiving user input and displaying the
 * appropriate information as per requested.
 * @author Fabian Chia Hup Peng
 */

public class Duke {

    private static final String filePath = "data/duke.txt";

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Creates a Duke instance with the appropriate attributes.
     * @param filePath The filepath to the text document for Duke.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = storage.loadList();
    }

    /**
     * Runs the Duke System. This includes parser user inputs into
     * appropriate commands to be carried out.
     */
    public void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
        }
    }

    /**
     * Creates a Duke instance to be run.
     * @param args The inputs to be read from the command line.
     */
    public static void main(String[] args) {
        new Duke(filePath).run();
    }

}


