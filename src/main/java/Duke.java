import java.io.IOException;

/**
 * Represents a Duke Chatbot that is able to keep track of tasks.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Create a new Duke Chatbot with access to duke.txt file.
     */
    public Duke() {
        String filePath = "duke.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadPreviousTasks());
        } catch (DukeException e) {
            ui.showLoadingError();
            storage = new Storage("duke.txt");
            taskList = new TaskList();
        }
    }

    /**
     * Main method that starts everything.
     * @param args String array
     */
    /*
    public static void main(String[] args) {
        //new Duke("duke.txt").run();
        new Duke().run();
    }
     */

    /**
     * Runs the Chatbot.
     */
    public String run(String command) {
        ui.showWelcome();
        System.out.println("Here is the list of tasks from where you've left off: ");
        ui.showList(taskList);

        Parser parser = new Parser(taskList, ui, storage);

        String response = parser.processCommand(command); //returns string?

        return response;
    }

    //REPLACE THIS WITH MY COMPLETED METHOD!!!!
    public String getResponse(String input) {
        //return "Duke heard: " + input;
        return run(input);
    }

}
