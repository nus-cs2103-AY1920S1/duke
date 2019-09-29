import java.io.IOException;

/**
 * Duke is the main body of the program. It takes in the
 * user's input and processes it accordingly.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit = false;

    public Duke() {

    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            tasks.updateQueue();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Processes the input from the user.
     * Takes the input from the user, and processes it according to the command and description if
     * necessary. The changes are updated in storage, and the response from the UI is returned as
     * a string.
     * @param input input from the user.
     * @return String Response to be displayed to the user.
     */
    public String run(String input) {
        Parser parser = new Parser(ui);

        try {
            String output = parser.parseCommand(input, tasks);
            storage.update(tasks);

            return output;

        } catch (DukeException e) {

            return ui.showException(e);

        } catch (IOException e) {
            return e.getMessage();
        }
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String output = run(input);
        assert output != null;
        return output;
    }
}