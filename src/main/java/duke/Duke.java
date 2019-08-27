package duke;

import duke.command.Command;
import duke.exception.InvalidCommandException;

import java.io.FileNotFoundException;
import java.text.ParseException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialize the Duke class.
     * This method should be executed after the creation
     * of the {@link MainWindow} object and before the
     * execution of {@link Duke#getResponse getResponse}.
     *
     * @param mainWindow the main window for the Duke GUI
     */
    public void initialize(MainWindow mainWindow) {
        this.ui = new Ui(mainWindow);
        this.storage = new Storage("duke.txt");
        try {
            this.tasks = storage.loadTasks();
        } catch (FileNotFoundException | ParseException ex) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
        this.ui.showWelcome();
    }

    /**
     * Get a response to a given command.
     * This method returns the response from Duke
     * given the command.
     *
     * @param fullCommand the command to be executed
     * @return true is the bye command is invoked, false otherwise
     */
    public boolean getResponse(String fullCommand) {
        try {
            Command command = Parser.parseCommand(fullCommand);
            return command.execute(this.tasks, this.ui, this.storage);
        } catch (ParseException e) {
            this.ui.showInvalidDateError();
        } catch (InvalidCommandException e) {
            this.ui.showInvalidCommandError();
        }

        return true;
    }

}
