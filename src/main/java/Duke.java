import java.util.logging.Logger;

/**
 * Main class of the application.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke instance that saves its data at a specified path.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("C:/Users/User/Desktop/duke.txt");

        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    /**
     * Retrieve a response from the user input and return the response that duke
     * will reply.
     *
     * @param input the user input text
     * @return the response text
     */
    public String getResponse(String input) {
        String inputIgnoreCase = input.toLowerCase();

        if (inputIgnoreCase.equals("bye")) {
            return "Bye. Hope to see you again soon!";
        } else {
            try {
                return Parser.parse(tasks, ui, inputIgnoreCase, storage);
            } catch (Exception err) {
                return "[Exception]" + err.getMessage();
            }
        }
    }
}
