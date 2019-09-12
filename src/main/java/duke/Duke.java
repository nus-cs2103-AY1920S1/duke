package duke;

import duke.command.Command;

import java.io.FileNotFoundException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new instance of the Duke assistant which stores the task list in the given file path.
     *
     * @deprecated because of the addition of the graphic interface.
     * @param filePath the file path to the task list file.
     */
    @Deprecated
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadMain());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        } catch (DukeException e) {
            ui.printError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Constructs a new instance of the Duke assistant which has a specified GUI and stores the task list in the
     * given file path.
     *
     * @param filePath the file path to the task list file.
     * @param window the GUI for Duke to interact with.
     */
    public Duke(String filePath, MainWindow window) {
        ui = new Ui(window);
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadMain());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        } catch (DukeException e) {
            ui.printError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Starts an instance to receive and execute prompts from the user using command line.
     *
     * @deprecated because of the addition of the graphic interface.
     */
    @Deprecated
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printError(e);
            }
        }
    }

    /**
     * Initialises a Duke instance and shows the welcome message.
     */
    public void init() {
        ui.showWelcome();
    }

    /**
     * Handles and executes a command entered by the user.
     *
     * @param fullCommand the command entered by the user.
     */
    public void handleUserCommand(String fullCommand) {
        try {
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            ui.printError(e);
        }
    }
}
