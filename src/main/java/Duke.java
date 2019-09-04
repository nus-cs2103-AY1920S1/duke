import command.Command;
import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Main Duke class.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean readyToExit = false;

    /**
     * Constructor for Duke Class.
     */
    public Duke() {
        String filePath = "list.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException duke) {
            ui.showLoadingError(duke);
            tasks = new TaskList();
        }
    }

    /**
     * Returns a String response by the bot based on the user input.
     *
     * @param input Input by the user.
     * @return Output response of String type.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.getResponse(tasks, ui, storage);
            if (c.isExit()) {
                this.setExit();
            }
            return response;
        } catch (DukeException e) {
            return ui.showLoadingError(e);
        }
    }

    /**
     * Sets the state of the program to be ready to exit
     * by the next user command.
     */
    private void setExit() {
        this.readyToExit = true;
    }

    /**
     * Ready to exit method.
     *
     * @return True if the User is ready to exit.
     */
    public boolean readyToExit() {
        return this.readyToExit;
    }

}
