package duke;

import duke.command.Command;

/**
 * Represents a Duke object. <code>Duke</code> is a virtual assistant which allows
 * users to input their todos, deadlines and events. Users can track the completion
 * of their tasks and view them in a readable list easily.
 */
public class Duke {
    private Storage storage;
    private ListStoreModel lists;
    private Ui ui;

    /**
     * Constructor for Duke instance that creates a task list from the given
     * file path and has a GUI.
     * @param filePath path of the storage txt file
     * @param window GUI on the ui.
     */
    public Duke(String filePath, MainWindow window) {
        ui = new Ui(window);
        storage = new Storage(filePath);
        lists = storage.load();
        ui.getInfoMessage(lists);
    }

    /**
     * Starts a program to take in user input and perform commands respectively.
     *
     * @deprecated after addition of GUI.
     */
    @Deprecated
    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(lists, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Initialises a Duke program and prints the welcome message.
     */
    public void init() {
        ui.showWelcome();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other
     * containing Duke's reply and then appends them to the dialog
     * container. Clears the user input after processing.
     */
    protected void handleUserCommand(String fullCommand) {
        try {
            Command c = Parser.parse(fullCommand);
            c.execute(lists, ui, storage);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}
