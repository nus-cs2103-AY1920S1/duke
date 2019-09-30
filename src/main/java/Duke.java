/**
 * Represents the initialisation and continuation of the program
 */

public class Duke {

    private Ui ui;
    private TaskList taskList;
    private Parser parser;

    /**
     * Initializes the Duke application and the storage file.
     */

    void DukeStart() {
        ui = new Ui();
        Storage storage = new Storage("data/savedTasks.txt");
        taskList = new TaskList(storage.load());
        parser = new Parser();
    }

    /**
     * Gets response when user enters an input.
     *
     * @param input User input for the command.
     * @return response to the User input.
     */

    String getResponse (String input) {
        try {
            parser.parse(input, ui, taskList);
        } catch (DukeException e) {
            return e.getMessage();
        }
        return ui.getOutput();
    }
}
